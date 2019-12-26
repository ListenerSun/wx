package com.sqt.edu.account.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sqt.edu.core.base.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2019-12-20 16:14
 */
@Builder
@ApiModel
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "test_table")
public class TestTable extends BaseModel {

    @ApiModelProperty("自增id")
    private Long id;
    @ApiModelProperty("姓名")
    private String name;
    @ApiModelProperty("邮箱")
    private String email;

}
