package com.sqt.edu.common.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-04-08 15:40
 */
@Slf4j
@Configuration
public class OssConfig {

    @Autowired
    private ConfigurableApplicationContext ctx;

    public OSS getOssClient() {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，创建RAM账号。
        String accessKeyId = "";
        String accessKeySecret = "";
        String bucketName = "";
        String ossUrl = "";
        ConfigurableEnvironment environment = ctx.getEnvironment();
        if (environment.containsProperty("oss.use") && Boolean.valueOf(environment.getProperty("oss.use"))) {
            endpoint = environment.getProperty("oss.endPoint");
            accessKeyId = environment.getProperty("oss.accessKeyId");
            accessKeySecret = environment.getProperty("oss.accessKeySecret");
            bucketName = environment.getProperty("oss.bucketName");
            ossUrl = environment.getProperty("oss.url");
            if (StringUtils.isBlank(endpoint)
                    || StringUtils.isBlank(accessKeyId)
                    || StringUtils.isBlank(accessKeySecret)
                    || StringUtils.isBlank(bucketName)
                    || StringUtils.isBlank(ossUrl)) {
                log.error("OSS相关参数有误,请检查!");
                return null;
            }

        }
        return new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    }
}