package com.sqt.edu.course.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-04-29 10:13
 */
@ApiModel("推荐课程返回VO")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DiscoverCourseVo implements Serializable {
    private static final long serialVersionUID = -2883600423727930670L;

    @ApiModelProperty("课程id")
    private Long id;
    @ApiModelProperty("课程logo地址")
    private String courseLogo;
}
