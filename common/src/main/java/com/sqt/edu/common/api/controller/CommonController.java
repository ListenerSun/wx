package com.sqt.edu.common.api.controller;

import com.sqt.edu.common.api.service.CommonService;
import com.sqt.edu.common.api.service.OssService;
import com.sqt.edu.common.base.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-04-08 13:56
 */
@Slf4j
@Api(tags = "【OSS-上传相关接口】")
@RestController
@RequestMapping("/common")
public class CommonController {

    @Autowired
    private OssService ossService;
    @Autowired
    private CommonService commonService;

    @ApiOperation(value = "COMMON-1.1-上传文件")
    @PostMapping("/oss/upload")
    public JsonResult upLoadFile(@RequestParam("file") MultipartFile imageFile) {

        return ossService.upLoadFile(imageFile);
    }

    @ApiOperation(value = "COMMON-1.2-上获取所有字典列表")
    @GetMapping("/dic/list")
    public JsonResult getAllDic(){
        return commonService.getAllDic();
    }



}
