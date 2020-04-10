package com.sqt.edu.common.api.service;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectRequest;
import com.sqt.edu.common.api.vo.OssReponseVO;
import com.sqt.edu.common.base.JsonResult;
import com.sqt.edu.common.config.OssAutoConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-04-08 15:34
 */
@Slf4j
@Service
public class OssService {

    private static final String OSS_PICTURE_FILE_PATH_NAME = "picture";
    private static final String OSS_OTHER_FILE_PATH_NAME = "other";

    @Autowired
    private OssAutoConfiguration ossConfig;

    @Value("${oss.bucketName}")
    private String bucketName;
    @Value("${oss.url}")
    private String ossUrl;

    /**
     * 上传文件到 Oss
     *
     * @param imageFile
     * @return
     */
    public JsonResult upLoadFile(MultipartFile imageFile) {
        OSS ossClient = getOssClient();
        // 创建PutObjectRequest对象。
        PutObjectRequest putObjectRequest = null;
        String filepPath = "";
        try {
            String savePath = getSavePath(imageFile.getOriginalFilename());
            filepPath = savePath + "/" + UUID.randomUUID().toString().replace("-", "") + "." + imageFile.getOriginalFilename().split("\\.")[1];
            putObjectRequest = new PutObjectRequest(bucketName, filepPath, imageFile.getInputStream());
            ObjectMetadata objectMetadata = getObjectMetadata(savePath);
            putObjectRequest.setMetadata(objectMetadata);
            // 上传文件。
            ossClient.putObject(putObjectRequest);
            // 关闭OSSClient。
            ossClient.shutdown();
        } catch (IOException e) {
            log.error("==========> OSSClient上传失败。异常信息:{}", e);
        }
        OssReponseVO ossResponseVo = new OssReponseVO(ossUrl + "/" + filepPath, filepPath,
                imageFile.getSize() / 1024);
        return new JsonResult(ossResponseVo);
    }

    /**
     * 存储文件不同 设置不同
     *
     * @param savePath
     * @return
     */
    private ObjectMetadata getObjectMetadata(String savePath) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        switch (savePath) {
            case OSS_PICTURE_FILE_PATH_NAME:
                objectMetadata.setContentType("image/jpg");
                return objectMetadata;
            default:
                break;
        }
        return objectMetadata;
    }

    /**
     * 根据图片名后缀查询存储文件夹
     *
     * @param fileName
     * @return
     */
    private String getSavePath(String fileName) {
        String endStr = fileName.split("\\.")[1];
        String path = "";
        switch (endStr) {
            case "jpg":
            case "jpeg":
            case "png":
            case "gif":
            case "swf":
            case "svg":
                path = OSS_PICTURE_FILE_PATH_NAME;
                break;
            default:
                path = OSS_OTHER_FILE_PATH_NAME;
                break;
        }

        return path;
    }


    private OSS getOssClient(){
        return ossConfig.getOssClient();
    }
}
