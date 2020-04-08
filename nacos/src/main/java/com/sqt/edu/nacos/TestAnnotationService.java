package com.sqt.edu.nacos;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.sqt.edu.common.base.JsonResult;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-04-03 14:04
 */
public class TestAnnotationService {
    @SentinelResource(value = "annotation_service")
    public JsonResult test_annotation(){
        return new JsonResult<>("hello annotation");
    }
}
