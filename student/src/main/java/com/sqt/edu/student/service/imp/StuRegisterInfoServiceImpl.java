package com.sqt.edu.student.service.imp;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.hutool.poi.excel.ExcelFileUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.sqt.edu.core.base.JsonResult;
import com.sqt.edu.core.base.ResultCode;
import com.sqt.edu.core.exception.ServiceException;
import com.sqt.edu.core.utils.RequestHelper;
import com.sqt.edu.student.Student_App;
import com.sqt.edu.student.constant.StudentEnum;
import com.sqt.edu.student.dto.request.QueryStuRegisterInfoDTO;
import com.sqt.edu.student.dto.request.StuRegisterInfoDTO;
import com.sqt.edu.student.dto.request.SubjectDTO;
import com.sqt.edu.student.dto.resp.StuRegisterInfoVo;
import com.sqt.edu.student.entity.ClassInfo;
import com.sqt.edu.student.entity.StuRegisterInfo;
import com.sqt.edu.student.mapper.ClassInfoMapper;
import com.sqt.edu.student.mapper.StuRegisterMapper;
import com.sqt.edu.student.service.StuRegisterInfoService;
import com.sqt.edu.student.utils.StudentCommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
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
        if (null == classInfo) {
            log.error("==========>补课班级信息不存在,id:{}", stuRegisterInfoDTO.getClassInfoId());
            throw new ServiceException(ResultCode.STU_CLASS_INFO_NOT_EXIST);
        }
        if (classInfo.getHasAmount() >= classInfo.getPlanAmount()) {
            log.warn("==========> 该班级名额已经报满!,id:{}", stuRegisterInfoDTO.getClassInfoId());
            throw new ServiceException(ResultCode.STU_CLASS_ORDER_FULL);
        }
        Map<SFunction<StuRegisterInfo, ?>, Object> param = new HashMap<>();
        param.put(StuRegisterInfo::getStudentName, stuRegisterInfoDTO.getStudentName());
        param.put(StuRegisterInfo::getPhone, stuRegisterInfoDTO.getPhone());
        StuRegisterInfo result = stuRegisterMapper.selectOne(Wrappers.<StuRegisterInfo>lambdaQuery().allEq(param));
        if (null != result) {
            log.warn("==========> 该学生已经报过名,studentName:{},phone:{}", stuRegisterInfoDTO.getStudentName(), stuRegisterInfoDTO.getPhone());
            throw new ServiceException(ResultCode.STU_SIGN_EXIST);
        }
        StuRegisterInfo stuRegisterInfo = new StuRegisterInfo();
        BeanUtils.copyProperties(stuRegisterInfoDTO, stuRegisterInfo);
        stuRegisterInfo.setYear(classInfo.getYear());
        stuRegisterMapper.insert(stuRegisterInfo);
        log.info("==========>学生:{}报名成功!", stuRegisterInfoDTO.getStudentName());
        classInfo.setHasAmount((classInfo.getHasAmount() + 1));
        if (classInfo.getPlanAmount().equals(classInfo.getHasAmount())) {
            classInfo.setEnrollState(StudentEnum.EnrollState.DONE.getCode());
        }
        classInfoMapper.updateById(classInfo);
        return new JsonResult();
    }

    @Override
    public JsonResult queryStuRegisterInfo(String phone, Integer year) {
        List<StuRegisterInfoVo> stuRegisterInfoVoList = stuRegisterMapper.queryByPhoneAndYear(phone, year);
        return new JsonResult(resolveToResult(stuRegisterInfoVoList));
    }

    @Override
    public JsonResult queryStuRegisterInfoList(QueryStuRegisterInfoDTO queryStuRegisterInfoDTO) {
        List<StuRegisterInfoVo> stuRegisterInfoVoList = stuRegisterMapper.queryStuRegisterInfoList(queryStuRegisterInfoDTO);
        return new JsonResult(resolveToResult(stuRegisterInfoVoList));
    }

    @Override
    public void exportExcel(List<Long> ids) {
        List<StuRegisterInfoVo> resultList = resolveToResult(stuRegisterMapper.exportExcel(ids));
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(),
                StuRegisterInfoVo.class, resultList);
        try {
            HttpServletResponse response = RequestHelper.getResponse();
            response.setHeader("content-Type", "application/vnd.ms-excel");
            // 下载文件的默认名称
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("用户数据表","UTF-8") +
                    ".xls");
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            log.error("==========> 导出异常! 异常信息:{}",e);
        }
    }

    /**
     * 转换  subjects
     *
     * @param stuRegisterInfoVoList
     * @return
     */
    private List<StuRegisterInfoVo> resolveToResult(List<StuRegisterInfoVo> stuRegisterInfoVoList) {
        if (null != stuRegisterInfoVoList) {
            stuRegisterInfoVoList.forEach(e -> {
                List<SubjectDTO> subjectDTOList = JSON.parseArray(e.getSubjects(), SubjectDTO.class);
                e.setSubjects(StudentCommonUtils.resolveSubjects(subjectDTOList));
            });
        }
        return stuRegisterInfoVoList;
    }

}
