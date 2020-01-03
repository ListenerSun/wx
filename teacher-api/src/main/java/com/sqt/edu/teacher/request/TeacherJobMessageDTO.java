package com.sqt.edu.teacher.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-01-03 16:34
 */
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("老师发布家教信息入参")
public class TeacherJobMessageDTO implements Serializable {
    private static final long serialVersionUID = -3321080219767007626L;
    @ApiModelProperty(value = "teacherJobMessageId")
    private Long teacherJobMessageId;
    @NotBlank(message = "学科参数不能为空!")
    @ApiModelProperty(value = "学科")
    private String subject;
    @NotBlank(message = "年级参数不能为空!")
    @ApiModelProperty(value = "年级")
    private String grade;
    @NotBlank(message = "价格参数不能为空!")
    @ApiModelProperty(value = "价格")
    private BigDecimal price;
    @NotBlank(message = "单位参数不能为空!")
    @ApiModelProperty(value = "单位: 分钟，小时,天，月。和价格配合使用")
    private String unit;
}
