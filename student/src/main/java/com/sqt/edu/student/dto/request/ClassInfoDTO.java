package com.sqt.edu.student.dto.request;

import com.sqt.edu.student.entity.SubjectInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-02-14 17:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ApiModel
public class ClassInfoDTO implements Serializable {

    @ApiModelProperty("补课班级信息id")
    private Long id;
    /**
     * 新增时使用
     */
    @ApiModelProperty("补课科目")
    private List<SubjectDTO> subjectList;
    @ApiModelProperty("班级名称")
    @NotBlank(message = "班级名称不能为空")
    private String className;
    @ApiModelProperty("年级")
    @NotBlank(message = "年级名称不能为空")
    private String grade;
    @ApiModelProperty("补课天数")
    @NotNull(message = "补课天数不能为空")
    private Integer days;
    @ApiModelProperty("年份")
    @NotNull(message = "年份不能为空")
    private Integer year;
    @ApiModelProperty("计划招收学生数量")
    @NotNull(message = "计划招生数量不能为空")
    private Integer planAmount;
    @ApiModelProperty("已报名人数")
    private Integer hasAmount;
    @ApiModelProperty("假期类型: 1:暑假,2:寒假")
    @NotBlank(message = "假期类型不能为空")
    private String vacationType;
    /**
     * 查询返回时使用
     */
    @ApiModelProperty("补课科目字符串")
    private String subjects;
}
