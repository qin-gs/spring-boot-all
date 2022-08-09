package com.example.polling;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ClientConfig {

   private static final Logger log = LoggerFactory.getLogger(ClientConfig.class);

    private CloseableHttpClient httpClient;
    private RequestConfig requestConfig;

    public ClientConfig() {
        httpClient = HttpClientBuilder.create().build();
        // 客户端超时时间要大于长轮询约定的超时时间
        requestConfig = RequestConfig.custom().setConnectTimeout(5000).setSocketTimeout(5_0000).build();
    }

    public void longPolling(String url, String dataId) throws IOException {
        String endPoint = url + "?dataId=" + dataId;
        HttpGet request = new HttpGet(endPoint);
        CloseableHttpResponse response = httpClient.execute(request);
        int statusCode = response.getStatusLine().getStatusCode();
        switch (statusCode) {
            case 200: {
                String result = IOUtils.toString(response.getEntity().getContent(), StandardCharsets.UTF_8);
                log.info("result: {}", result);
                response.close();
                longPolling(url, dataId);
                break;
            }
            case 304: {
                // 数据没改变
                log.info(" {} 数据未改变", dataId);
                longPolling(url, dataId);
                break;
            }
            default:
                log.error("状态码不对：{}", statusCode);
                break;
        }
    }

    public static void main(String[] args) throws IOException {

        ClientConfig client = new ClientConfig();
        // 开启一个监听
        client.longPolling("http://localhost:8081/listener", "user");
    }
}
