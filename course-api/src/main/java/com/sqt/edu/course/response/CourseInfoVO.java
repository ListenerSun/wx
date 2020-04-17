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
 * @Date: Created in 2020-04-17 15:01
 */
@ApiModel("课程章节详情")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseInfoVO implements Serializable {
    private static final long serialVersionUID = -3214549274574246460L;

    @ApiModelProperty(value = "章节")
    private Integer chapterOrder;
    @ApiModelProperty(value = "资源地址")
    private String resourceUrl;
    @ApiModelProperty(value = "是否免费")
    private Boolean isFree;
    @ApiModelProperty(value = "课程名字")
    private String videoName;
}
