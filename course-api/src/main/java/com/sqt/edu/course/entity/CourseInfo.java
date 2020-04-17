package com.sqt.edu.course.entity;

import com.sqt.edu.common.base.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 课程章节详情
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-04-15 10:52
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseInfo extends BaseModel {
    private static final long serialVersionUID = -1337554698334856591L;

    @ApiModelProperty(value = "章节")
    private Integer chapterOrder;
    @ApiModelProperty(value = "课程id")
    private Long courseId;
    @ApiModelProperty(value = "资源地址")
    private String resourceUrl;
    @ApiModelProperty(value = "是否免费")
    private Boolean isFree;
    @ApiModelProperty(value = "课程名字")
    private String videoName;
}