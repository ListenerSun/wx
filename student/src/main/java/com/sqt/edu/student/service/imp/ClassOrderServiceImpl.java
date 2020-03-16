package com.sqt.edu.student.service.imp;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.sqt.edu.core.base.JsonResult;
import com.sqt.edu.core.base.ResultCode;
import com.sqt.edu.core.exception.ServiceException;
import com.sqt.edu.student.dto.request.ClassOrderDTO;
import com.sqt.edu.student.entity.ClassInfo;
import com.sqt.edu.student.entity.ClassOrder;
import com.sqt.edu.student.mapper.ClassInfoMapper;
import com.sqt.edu.student.mapper.ClassOrderMapper;
import com.sqt.edu.student.service.ClassOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-02-13 23:29
 */
@Slf4j
@Service
public class ClassOrderServiceImpl implements ClassOrderService {

    @Autowired
    private ClassOrderMapper classOrderMapper;
    @Autowired
    private ClassInfoMapper classInfoMapper;

    @Override
    public JsonResult add(ClassOrderDTO classOrderDTO) {
        ClassInfo classInfo = classInfoMapper.selectById(classOrderDTO.getClassInfoId());
        if (null == classInfo){
            log.error("==========>补课班级信息不存在,classInfoId:{}",classOrderDTO.getClassInfoId());
            throw new ServiceException(ResultCode.STU_CLASS_INFO_NOT_EXIST);
        }
        if (classInfo.getHasAmount() >= classInfo.getPlanAmount()){
            log.warn("==========> 该班级名额已经报满!,classInfoId:{}",classOrderDTO.getClassInfoId());
            throw new ServiceException(ResultCode.STU_CLASS_ORDER_FULL);
        }
        Map<SFunction<ClassOrder,?>,Object> param = new HashMap<>();
        param.put(ClassOrder::getStudentName,classOrderDTO.getStudentName());
        param.put(ClassOrder::getPhone,classOrderDTO.getPhone());
        ClassOrder result = classOrderMapper.selectOne(Wrappers.<ClassOrder>lambdaQuery().allEq(param));
        if (null != result){
            log.warn("==========> 该学生已经报过名,studentName:{},phone:{}",classOrderDTO.getStudentName(),classOrderDTO.getPhone());
            throw new ServiceException(ResultCode.STU_SIGN_EXIST);
        }
        ClassOrder classOrder = ClassOrder.builder()
                .grade(classOrderDTO.getGrade())
                .phone(classOrderDTO.getPhone())
                .studentName(classOrderDTO.getStudentName())
                .classInfoId(classOrderDTO.getClassInfoId())
                .build();
        classOrderMapper.insert(classOrder);
        log.info("==========>学生:{}报名成功!",classOrderDTO.getStudentName());
        classInfo.setHasAmount((classInfo.getHasAmount()+1));
        classInfoMapper.updateById(classInfo);
        return new JsonResult();
    }
}
