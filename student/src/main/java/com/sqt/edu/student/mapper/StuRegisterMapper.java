package com.sqt.edu.student.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sqt.edu.student.dto.request.QueryStuRegisterInfoDTO;
import com.sqt.edu.student.dto.resp.StuRegisterInfoVo;
import com.sqt.edu.student.entity.StuRegisterInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-02-13 23:24
 */
public interface StuRegisterMapper extends BaseMapper<StuRegisterInfo> {


    /** query by phone and studentName
     * @param phone
     * @param year
     * @return
     */
    List<StuRegisterInfoVo> queryByPhoneAndYear(@Param("phone") String phone, @Param("year")Integer year);

    /**查询列表
     * @param queryStuRegisterInfoDTO
     * @return
     */
    List<StuRegisterInfoVo> queryStuRegisterInfoList(QueryStuRegisterInfoDTO queryStuRegisterInfoDTO);
}
