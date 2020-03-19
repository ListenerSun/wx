package com.sqt.edu.student.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-03-16 22:32
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ApiModel
public class QueryStuRegisterInfoDTO implements Serializable {
    private static final long serialVersionUID = 5647440559967649264L;

    @ApiModelProperty("联系方式")
    private String phone;
    @ApiModelProperty("学生姓名")
    private String studentName;
    @ApiModelProperty("年份")
    private Integer year;
    @ApiModelProperty("班级名称")
    private String className;

}
