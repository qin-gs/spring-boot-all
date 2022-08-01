package com.example.bean;

import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * webUpload 文件上传
 */
public class FileUpload {

    private String id;
    private String name;
    private String type;
    private Date lastModifiedDate;
    private Long size;
    private Integer chunks;
    private Integer chunk;
    private MultipartFile upload;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Integer getChunks() {
        return chunks;
    }

    public void setChunks(Integer chunks) {
        this.chunks = chunks;
    }

    public Integer getChunk() {
        return chunk;
    }

    public void setChunk(Integer chunk) {
        this.chunk = chunk;
    }

    public MultipartFile getUpload() {
        return upload;
    }

    public void setUpload(MultipartFile upload) {
        this.upload = upload;
    }

    @Override
    public String toString() {
        return "FileUpload{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", lastModifiedDate=" + lastModifiedDate +
                ", size=" + size +
                ", chunks=" + chunks +
                ", chunk=" + chunk +
                ", upload=" + upload +
                '}';
    }
}
