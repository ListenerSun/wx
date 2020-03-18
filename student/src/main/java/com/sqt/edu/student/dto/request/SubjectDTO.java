package com.sqt.edu.student.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-03-18 22:27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ApiModel
public class SubjectDTO implements Serializable {
    @ApiModelProperty("科目name")
    private String name;
    @ApiModelProperty("科目code")
    private String code;
}
