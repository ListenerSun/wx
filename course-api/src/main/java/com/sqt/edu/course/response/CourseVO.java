package com.sqt.edu.course.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-01-20 10:47
 */
@ApiModel("课程详细信息")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseVO implements Serializable {

    private static final long serialVersionUID = 854098411931329669L;
    @ApiModelProperty(value = "课程id")
    private Long id;
    @ApiModelProperty(value = "课程名字")
    private String courseName;
    @ApiModelProperty(value = "课程价格")
    private BigDecimal coursePrice;
    @ApiModelProperty(value = "打折价格")
    private BigDecimal discountPrice;
    @ApiModelProperty(value = "课程Logo")
    private String courseLogo;
    @ApiModelProperty(value = "是否免费")
    private Boolean isFree;
    @ApiModelProperty(value = "已购买数")
    private Integer countBuy;
    @ApiModelProperty(value = "已学习的人数")
    private Integer countStudy;
    @ApiModelProperty(value = "课程上架状态")
    private String courseState;
    @ApiModelProperty(value = "课程类型")
    private String courseType;
}
