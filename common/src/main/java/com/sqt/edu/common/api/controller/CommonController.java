package com.sqt.edu.common.api.controller;

import com.sqt.edu.common.api.service.OssService;
import com.sqt.edu.common.base.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-04-08 13:56
 */
@Slf4j
@Api(tags = "【OSS-上传相关接口】")
@RestController
@RequestMapping("/common/oss")
public class CommonController {

    @Autowired
    private OssService ossService;

    @ApiOperation(value = "OSS-1.1-上传文件")
    @PostMapping("/upload")
    public JsonResult upLoadFile(@RequestParam("file") MultipartFile imageFile) {

        return ossService.upLoadFile(imageFile);
    }



}
