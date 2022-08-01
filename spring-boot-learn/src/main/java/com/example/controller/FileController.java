package com.example.controller;

import com.example.bean.FileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Controller
public class FileController {

    private final static String UTF8 = "utf-8";

    @Value("${upload.path}")
    private String filePath;

    @GetMapping("file-upload")
    public String index() {
        return "file-upload";
    }

    /**
     * 分片上传，存的时候按顺序来
     * <p>
     * 如果不返回数据，需要加上 @ResponseBody，否则会找不到页面
     */
    @PostMapping("upload")
    @ResponseBody
    public void upload(FileUpload upload, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String name = upload.getName();
        // 文件下标
        Integer chunk = upload.getChunk();
        FileUtils.copyInputStreamToFile(upload.getUpload().getInputStream(),
                new File(filePath + name + "_" + chunk));
    }

    /**
     * 分片上传完成后，合并文件
     */
    @PostMapping("merge")
    @ResponseBody
    public void merge(@RequestParam String fileName) throws Exception {
        File file = new File(filePath);
        List<File> files = Arrays.stream(file.listFiles((dir, name) -> name.startsWith(fileName)))
                .sorted((o1, o2) -> {
                    Integer i1 = Integer.parseInt(o1.getName().substring(o1.getName().lastIndexOf("_") + 1));
                    Integer i2 = Integer.parseInt(o2.getName().substring(o2.getName().lastIndexOf("_") + 1));
                    return i1 - i2;
                }).collect(Collectors.toList());
        // 组合文件
        try (FileChannel channel = new FileOutputStream(filePath + fileName).getChannel()) {
            for (File f : files) {
                try (FileChannel inChannel = new FileInputStream(f).getChannel()) {
                    channel.transferFrom(inChannel, channel.size(), inChannel.size());
                    // 或者
                    // inChannel.transferTo(0, inChannel.size(), channel);
                }
                // 删除分片
                f.delete();
            }
        }

    }

    @PostMapping("download")
    public void download(HttpServletRequest request, HttpServletResponse response, @RequestParam String fileNa_me) throws Exception {
        response.setCharacterEncoding(UTF8);
        // 定义文件路径
        File file = new File(filePath + fileNa_me);
        InputStream is = null;
        OutputStream os = null;
        try {
            // 分片下载
            long fSize = file.length();// 获取长度
            response.setContentType("application/x-download");
            String fileName = URLEncoder.encode(file.getName(), StandardCharsets.UTF_8);
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
            // 根据前端传来的Range  判断支不支持分片下载
            response.setHeader("Accept-Range", "bytes");
            // 获取文件大小
            response.setHeader("fSize", String.valueOf(fSize));
            response.setHeader("fName", fileName);
            // 定义断点
            long pos = 0, last = fSize - 1, sum = 0;
            // 判断前端需不需要分片下载
            if (null != request.getHeader("Range")) {
                response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
                String numRange = request.getHeader("Range").replaceAll("bytes=", "");
                String[] strRange = numRange.split("-");
                if (strRange.length == 2) {
                    pos = Long.parseLong(strRange[0].trim());
                    last = Long.parseLong(strRange[1].trim());
                    // 若结束字节超出文件大小 取文件大小
                    if (last > fSize - 1) {
                        last = fSize - 1;
                    }
                } else {
                    // 若只给一个长度  开始位置一直到结束
                    pos = Long.parseLong(numRange.replaceAll("-", "").trim());
                }
            }
            long rangeLength = last - pos + 1;
            String contentRange = "bytes" + pos + "-" + last + "/" + fSize;
            response.setHeader("Content-Range", contentRange);
            response.setHeader("Content-Lenght", String.valueOf(rangeLength));
            os = new BufferedOutputStream(response.getOutputStream());
            is = new BufferedInputStream(new FileInputStream(file));
            is.skip(pos);// 跳过已读的文件
            byte[] buffer = new byte[1024];
            int length = 0;
            // 相等证明读完
            while (sum < rangeLength) {
                length = is.read(buffer, 0, (rangeLength - sum) <= buffer.length ? (int) (rangeLength - sum) : buffer.length);
                sum = sum + length;
                os.write(buffer, 0, length);

            }
            System.out.println("下载完成");
        } finally {
            if (is != null) {
                is.close();
            }
            if (os != null) {
                os.close();
            }
        }

    }

    // --------------------

    private final static long PER_PAGE = 1024L * 1024 * 50;
    /**
     * 分片存储临时目录 当分片下载完后在目录中找到文件合并
     */
    private final static String DOWN_PATH = "D:\\File";
    /**
     * 多线程下载
     */
    ExecutorService pool = Executors.newFixedThreadPool(10);

    /**
     * 文件大小 分片数量 文件名称
     * 使用探测 获取变量
     * 使用多线程分片下载
     * 最后一个分片下载完 开始合并
     */
    @RequestMapping("/downloadFile")
    public String downloadFile() throws IOException {
        FileInfo fileInfo = download(0, 10, -1, null);
        if (fileInfo != null) {
            long pages = fileInfo.fSize / PER_PAGE;
            for (int i = 0; i <= pages; i++) {
                pool.submit(new Download(i * PER_PAGE, (i + 1) * PER_PAGE - 1, i, fileInfo.fName));
            }
        }

        return "成功";
    }

    class Download implements Runnable {
        long start;
        long end;
        long page;
        String fName;

        public Download(long start, long end, long page, String fName) {
            this.start = start;
            this.end = end;
            this.page = page;
            this.fName = fName;
        }

        @Override
        public void run() {
            try {
                FileInfo fileInfo = download(start, end, page, fName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 返回文件名 跟大小
     */
    private FileInfo download(long start, long end, long page, String fName) throws IOException {
        // 断点下载 文件存在不需要下载
        File file = new File(DOWN_PATH, page + "-" + fName);
        // 探测必须放行 若下载分片只下载一半就锻炼需要重新下载所以需要判断文件是否完整
        if (file.exists() && page != -1 && file.length() == PER_PAGE) {
            return null;
        }
        // 需要知道  开始-结束 = 分片大小
        HttpClient client = HttpClients.createDefault();
        // httpclient进行请求
        HttpGet httpGet = new HttpGet("http://127.0.0.1:8080/down");
        // 告诉服务端做分片下载
        httpGet.setHeader("Range", "bytes=" + start + "-" + end);
        HttpResponse response = client.execute(httpGet);
        String fSize = response.getFirstHeader("fSize").getValue();
        fName = URLDecoder.decode(response.getFirstHeader("fName").getValue(), StandardCharsets.UTF_8);
        HttpEntity entity = response.getEntity();// 获取文件流对象
        InputStream is = entity.getContent();
        // 临时存储分片文件
        FileOutputStream fos = new FileOutputStream(file);
        byte[] buffer = new byte[1024];// 定义缓冲区
        int ch;
        while ((ch = is.read(buffer)) != -1) {
            fos.write(buffer, 0, ch);
        }
        is.close();
        fos.flush();
        fos.close();
        // 判断是不是最后一个分片
        if (end - Long.parseLong(fSize) > 0) {
            // 合并
            try {
                mergeFile(fName, page);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new FileInfo(Long.parseLong(fSize), fName);
    }

    private void mergeFile(String fName, long page) throws Exception {
        // 归并文件位置
        File file = new File(DOWN_PATH, fName);
        BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(file));
        for (int i = 0; i <= page; i++) {
            File tempFile = new File(DOWN_PATH, i + "-" + fName);
            // 分片没下载或者没下载完需要等待
            while (!file.exists() || (i != page && tempFile.length() < PER_PAGE)) {
                Thread.sleep(100);
            }
            byte[] bytes = FileUtils.readFileToByteArray(tempFile);
            os.write(bytes);
            os.flush();
            tempFile.delete();
        }
        File file1 = new File(DOWN_PATH, -1 + "-null");
        file1.delete();
        os.flush();
        os.close();
    }

    /**
     * 使用内部类实现
     */
    class FileInfo {
        long fSize;
        String fName;

        public FileInfo(long fSize, String fName) {
            this.fSize = fSize;
            this.fName = fName;
        }
    }
}
