package com.sqt.edu.student.service.imp;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.sqt.edu.core.base.JsonResult;
import com.sqt.edu.core.base.ResultCode;
import com.sqt.edu.core.exception.ServiceException;
import com.sqt.edu.student.dto.request.QueryStuRegisterInfoDTO;
import com.sqt.edu.student.dto.request.StuRegisterInfoDTO;
import com.sqt.edu.student.entity.ClassInfo;
import com.sqt.edu.student.entity.StuRegisterInfo;
import com.sqt.edu.student.mapper.ClassInfoMapper;
import com.sqt.edu.student.mapper.StuRegisterMapper;
import com.sqt.edu.student.service.StuRegisterInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-02-13 23:29
 */
@Slf4j
@Service
public class StuRegisterInfoServiceImpl implements StuRegisterInfoService {

    @Autowired
    private StuRegisterMapper stuRegisterMapper;
    @Autowired
    private ClassInfoMapper classInfoMapper;

    @Override
    public JsonResult add(StuRegisterInfoDTO stuRegisterInfoDTO) {
        ClassInfo classInfo = classInfoMapper.selectById(stuRegisterInfoDTO.getClassInfoId());
        if (null == classInfo){
            log.error("==========>补课班级信息不存在,id:{}", stuRegisterInfoDTO.getClassInfoId());
            throw new ServiceException(ResultCode.STU_CLASS_INFO_NOT_EXIST);
        }
        if (classInfo.getHasAmount() >= classInfo.getPlanAmount()){
            log.warn("==========> 该班级名额已经报满!,id:{}", stuRegisterInfoDTO.getClassInfoId());
            throw new ServiceException(ResultCode.STU_CLASS_ORDER_FULL);
        }
        Map<SFunction<StuRegisterInfo,?>,Object> param = new HashMap<>();
        param.put(StuRegisterInfo::getStudentName, stuRegisterInfoDTO.getStudentName());
        param.put(StuRegisterInfo::getPhone, stuRegisterInfoDTO.getPhone());
        StuRegisterInfo result = stuRegisterMapper.selectOne(Wrappers.<StuRegisterInfo>lambdaQuery().allEq(param));
        if (null != result){
            log.warn("==========> 该学生已经报过名,studentName:{},phone:{}", stuRegisterInfoDTO.getStudentName(), stuRegisterInfoDTO.getPhone());
            throw new ServiceException(ResultCode.STU_SIGN_EXIST);
        }
        StuRegisterInfo stuRegisterInfo = new StuRegisterInfo();
        BeanUtils.copyProperties(stuRegisterInfoDTO,stuRegisterInfo);
        stuRegisterMapper.insert(stuRegisterInfo);
        log.info("==========>学生:{}报名成功!", stuRegisterInfoDTO.getStudentName());
        classInfo.setHasAmount((classInfo.getHasAmount()+1));
        classInfoMapper.updateById(classInfo);
        return new JsonResult();
    }

    @Override
    public JsonResult queryStuRegisterInfo(QueryStuRegisterInfoDTO queryStuRegisterInfoDTO) {
        StuRegisterInfo stuRegisterInfo = stuRegisterMapper.selectOne(Wrappers.<StuRegisterInfo>lambdaQuery()
                .eq(StuRegisterInfo::getPhone, queryStuRegisterInfoDTO.getPhone())
                .eq(StuRegisterInfo::getStudentName, queryStuRegisterInfoDTO.getStudentName()));
        if (null == stuRegisterInfo){
            return new JsonResult(ResultCode.STU_REGISTER_INFO_NOT_EXIST);
        }
        return new JsonResult();
    }
}
