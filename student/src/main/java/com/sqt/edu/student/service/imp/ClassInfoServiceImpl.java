package com.sqt.edu.student.service.imp;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.sqt.edu.core.base.JsonResult;
import com.sqt.edu.core.base.ResultCode;
import com.sqt.edu.core.exception.ServiceException;
import com.sqt.edu.student.constant.StudentEnum;
import com.sqt.edu.student.dto.request.ClassInfoDTO;
import com.sqt.edu.student.dto.request.QueryClassInfoDTO;
import com.sqt.edu.student.dto.request.SubjectDTO;
import com.sqt.edu.student.entity.ClassInfo;
import com.sqt.edu.student.entity.StuRegisterInfo;
import com.sqt.edu.student.mapper.ClassInfoMapper;
import com.sqt.edu.student.mapper.StuRegisterMapper;
import com.sqt.edu.student.service.ClassInfoService;
import com.sqt.edu.student.utils.StudentCommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-02-14 17:20
 */
@Slf4j
@Service
public class ClassInfoServiceImpl implements ClassInfoService {

    private static final Integer HAS_AMOUNT_INIT = 0;

    @Autowired
    private ClassInfoMapper classInfoMapper;
    @Autowired
    private StuRegisterMapper stuRegisterMapper;

    @Override
    public JsonResult add(ClassInfoDTO classInfoDTO) {
        List<SubjectDTO> subjectList = classInfoDTO.getSubjectList();
        ClassInfo classInfo = ClassInfo.builder()
                .className(classInfoDTO.getClassName())
                .grade(classInfoDTO.getGrade())
                .days(classInfoDTO.getDays())
                .planAmount(classInfoDTO.getPlanAmount())
                .hasAmount(HAS_AMOUNT_INIT)
                .vacationType(classInfoDTO.getVacationType())
                .subjects(null == subjectList ? null : JSON.toJSONString(subjectList))
                .year(classInfoDTO.getYear())
                .build();
        classInfoMapper.insert(classInfo);
        log.info("==========> 新增补课班级信息成功! id:{}", classInfo.getId());
        return new JsonResult();
    }

    @Override
    public JsonResult update(ClassInfoDTO classInfoDTO) {
        ClassInfo classInfo = classInfoMapper.selectById(classInfoDTO.getId());
        if (null == classInfo) {
            log.error("==========> 补课班级信息不存在! id:{}", classInfoDTO.getId());
            throw new ServiceException(ResultCode.STU_CLASS_INFO_NOT_EXIST);
        }
        BeanUtils.copyProperties(classInfoDTO, classInfo);
        classInfo.setSubjects(null == classInfoDTO.getSubjectList() ? null : JSON.toJSONString(classInfoDTO.getSubjectList()));
        classInfoMapper.updateById(classInfo);
        log.info("==========> 更新补课班级信息成功! id:{}", classInfo.getId());
        return new JsonResult();
    }

    @Override
    public JsonResult delete(Long classInfoId) {
        stuRegisterMapper.delete(Wrappers.<StuRegisterInfo>lambdaQuery().eq(StuRegisterInfo::getClassInfoId,
                classInfoId));
        classInfoMapper.deleteById(classInfoId);
        log.info("==========> 删除补课班级信息成功! id:{}", classInfoId);
        return new JsonResult();
    }

    @Override
    public JsonResult list(QueryClassInfoDTO queryClassInfoDTO) {
        Map<SFunction<ClassInfo, ?>, Object> param = new HashMap<>();
        param.put(ClassInfo::getYear, queryClassInfoDTO.getYear());
        param.put(ClassInfo::getGrade, queryClassInfoDTO.getGrade());
        List<ClassInfoDTO> classInfoDTOList =
                classInfoMapper.list(queryClassInfoDTO);
        classInfoDTOList.forEach(classInfoDTO -> {
            if (StringUtils.isNotEmpty(classInfoDTO.getSubjects())) {
                List<SubjectDTO> subjectList = JSON.parseArray(classInfoDTO.getSubjects(), SubjectDTO.class);
                classInfoDTO.setSubjectList(subjectList);
                classInfoDTO.setSubjects(StudentCommonUtils.resolveSubjects(subjectList));
            }
        });
        return new JsonResult(classInfoDTOList);
    }

    @Override
    public JsonResult enrollClass(Long classInfoId) {
        ClassInfo classInfo = classInfoMapper.selectById(classInfoId);
        if (null == classInfo) {
            throw new ServiceException(ResultCode.STU_CLASS_INFO_NOT_EXIST);
        }
        classInfo.setEnrollState(StudentEnum.EnrollState.ING.getCode());
        classInfoMapper.updateById(classInfo);
        log.info("==========>发布招生成功,补课班级信息id:{}", classInfoId);
        return new JsonResult();
    }

}
