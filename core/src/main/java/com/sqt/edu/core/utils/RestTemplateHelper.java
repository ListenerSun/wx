package com.sqt.edu.core.utils;


import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Map;

/**
 * RestHelper
 *
 * @author sqt
 * @date 19-5-10
 */
@Component
@Slf4j
public class RestTemplateHelper {

    @Autowired
    private RestTemplate template;


    /**
     * post请求
     *
     * @param url
     * @param request
     * @param responseType
     * @param params
     * @param headers
     * @param <T>
     * @return
     */
    public <T> T post(String url, Object request, Class<T> responseType, Map<String, Object> params, Map<String, String> headers) {
        return exchange(buildUrl(url, params), HttpMethod.POST, request, responseType, params, headers);
    }

    /**
     * put请求
     *
     * @param url
     * @param request
     * @param responseType
     * @param params
     * @param headers
     * @param <T>
     * @return
     */
    public <T> T put(String url, Object request, Class<T> responseType, Map<String, Object> params, Map<String, String> headers) {
        return exchange(buildUrl(url, params), HttpMethod.PUT, request, responseType, params, headers);
    }

    /**
     * get 请求
     *
     * @param url
     * @param responseType
     * @param params
     * @param <T>
     * @return
     */
    public <T> T get(String url, Class<T> responseType, Map<String, Object> params, Map<String, String> headers) {
        return exchange(buildUrl(url, params), HttpMethod.GET, null, responseType, params, headers);
    }


    /**
     * delete 请求
     *
     * @param url
     * @param responseType
     * @param params
     * @param <T>
     * @return
     */
    public <T> T delete(String url, Class<T> responseType, Map<String, Object> params, Map<String, String> headers) {
        return exchange(buildUrl(url, params), HttpMethod.DELETE, null, responseType, params, headers);
    }


    /**
     * 统一访问接口
     *
     * @param url
     * @param method
     * @param request
     * @param responseType
     * @param params
     * @param <T>
     * @return
     */
    private <T> T exchange(String url,
                           HttpMethod method,
                           Object request,
                           Class<T> responseType,
                           Map<String, Object> params,
                           Map<String, String> headerMap) {
        // 请求头
        HttpHeaders headers = new HttpHeaders();
        MimeType mimeType = MimeTypeUtils.parseMimeType("application/json");
        MediaType mediaType = new MediaType(mimeType.getType(), mimeType.getSubtype(), Charset.forName("UTF-8"));

        // 请求体
        headers.setContentType(mediaType);
        if (headerMap != null && !CollectionUtils.isEmpty(headerMap)) {
            for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                headers.set(entry.getKey(), entry.getValue());
            }
        }
//        //判断header中是存在
////        List<String> key = headers.get(AuthConstants.HTTP_HEADER_APIKEY);
////        if (CollectionUtils.isEmpty(key)) {
////            //写入默认的apiKey
////            headers.set(AuthConstants.HTTP_HEADER_APIKEY, ApiKeyCntUtils.buildDefaultApikeyCnt());
////        }
        //提供json转化功能
        String json = null;
        if (request != null) {
            if (json instanceof String) {
                json = request.toString();
            } else {
                try {
                    json = JSON.toJSONString(request);
                } catch (Exception e) {
                    throw new RuntimeException("JSON.toJSONString failed,errmessage=" + e.getMessage());
                }
            }
        }

        // 发送请求
        HttpEntity<String> entity = new HttpEntity<>(json, headers);
        ResponseEntity<T> resultEntity = null;

        long seqId = System.nanoTime();

        log.info("准备发送请求（批次号为 " + seqId + "），请求url为 " + url);
        log.info("准备发送请求（批次号为 " + seqId + "），请求明细为 " + JSON.toJSONString(entity));

        if (params == null || params.size() == 0) {
            resultEntity = template.exchange(url, method, entity, responseType);
        } else {
            resultEntity = template.exchange(url, method, entity, responseType, params);
        }

        return resultEntity.getBody();
    }

    private String buildUrl(String url, Map<String, Object> params) {
        StringBuilder builder = new StringBuilder();
        builder.append(url);
        if (params == null || params.size() == 0) {
            return builder.toString();
        }
        builder.append("?");
        for (String key : params.keySet()) {
            builder.append(key).append("=");
            builder.append("{").append(key).append("}").append("&");
        }
        return builder.toString().substring(0, builder.toString().length() - 1);
    }
}
