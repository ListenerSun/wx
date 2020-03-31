package com.sqt.edu.teacher.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sqt.edu.core.base.BaseModel;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 讲师评论表
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-04-01 0:49
 */
@ApiModel
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "teacher_comment")
public class TeacherComments extends BaseModel {
    private static final long serialVersionUID = -6935366661149087634L;
}
