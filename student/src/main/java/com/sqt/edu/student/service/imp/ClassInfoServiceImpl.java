package com.sqt.edu.student.service.imp;

import com.sqt.edu.core.base.JsonResult;
import com.sqt.edu.core.base.ResultCode;
import com.sqt.edu.core.exception.ServiceException;
import com.sqt.edu.student.dto.request.ClassInfoDTO;
import com.sqt.edu.student.entity.ClassInfo;
import com.sqt.edu.student.mapper.ClassInfoMapper;
import com.sqt.edu.student.service.ClassInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public JsonResult add(ClassInfoDTO classInfoDTO) {
        ClassInfo classInfo = ClassInfo.builder()
                .grade(classInfoDTO.getGrade())
                .days(classInfoDTO.getDays())
                .planAmount(classInfoDTO.getPlanAmount())
                .hasAmount(HAS_AMOUNT_INIT)
                .vacationType(classInfoDTO.getVacationType())
                .subjects(classInfoDTO.getSubjects())
                .year(classInfoDTO.getYear())
                .build();
        classInfoMapper.insert(classInfo);
        log.info("==========> 新增补课班级信息成功! classInfoId:{}",classInfo.getId());
        return new JsonResult();
    }

    @Override
    public JsonResult update(ClassInfoDTO classInfoDTO) {
        ClassInfo classInfo = classInfoMapper.selectById(classInfoDTO.getClassInfoId());
        if (null == classInfo){
            log.error("==========> 补课班级信息不存在! classInfoId:{}",classInfoDTO.getClassInfoId());
            throw new ServiceException(ResultCode.STU_CLASS_INFO_NOT_EXIST);
        }
        BeanUtils.copyProperties(classInfoDTO,classInfo);
        classInfoMapper.updateById(classInfo);
        log.info("==========> 更新补课班级信息成功! classInfoId:{}",classInfo.getId());
        return new JsonResult();
    }

    @Override
    public JsonResult delete(Long classInfoId) {
        classInfoMapper.deleteById(classInfoId);
        log.info("==========> 删除补课班级信息成功! classInfoId:{}",classInfoId);
        return new JsonResult();
    }
}
