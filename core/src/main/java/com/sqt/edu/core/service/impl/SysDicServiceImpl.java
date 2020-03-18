package com.sqt.edu.core.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.sqt.edu.core.entity.SysDicData;
import com.sqt.edu.core.mapper.SysDicDataMapper;
import com.sqt.edu.core.mapper.SysDicTypeMapper;
import com.sqt.edu.core.service.SysDicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-03-18 10:07
 */
@Slf4j
@Service
public class SysDicServiceImpl implements SysDicService {

    @Autowired
    private SysDicDataMapper sysDicDataMapper;
    @Autowired
    private SysDicTypeMapper sysDicTypeMapper;

    @Override
    public List<SysDicData> queryByDicTypeCode(String dicTypeCode) {
        List<SysDicData> sysDicDataList = sysDicDataMapper.selectList(Wrappers.<SysDicData>lambdaQuery().eq(SysDicData::getDicTypeCode, dicTypeCode));
        return sysDicDataList;
    }
}
