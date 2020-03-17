package com.sqt.edu.student.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Description: @Lin
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-03-17 23:11
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ApiModel
public class QueryClassInfoDTO implements Serializable {

    private static final long serialVersionUID = 5157633412964304829L;

    @ApiModelProperty("年级")
    private String grade;
    @ApiModelProperty("年份")
    private Integer year;
}
