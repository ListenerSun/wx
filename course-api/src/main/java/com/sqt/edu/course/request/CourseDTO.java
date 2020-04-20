package com.sqt.edu.course.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description: 课程DTO
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-01-13 14:42
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel
public class CourseDTO implements Serializable {

    @NotBlank(message = "课程名字不能为空!")
    @ApiModelProperty(value = "课程名字")
    private String courseName;
    @NotNull(message = "课程价格不能为空!")
    @ApiModelProperty(value = "课程价格")
    private BigDecimal coursePrice;
    @ApiModelProperty(value = "课程Logo")
    private String courseLogo;
    @ApiModelProperty(value = "是否免费")
    private Boolean isFree;
    @ApiModelProperty(value = "是否视频")
    private Boolean isVideo;
    @ApiModelProperty(value ="课程类型")
    private String courseType;
    @ApiModelProperty(value ="讲师id")
    private Long teacherId;
}
