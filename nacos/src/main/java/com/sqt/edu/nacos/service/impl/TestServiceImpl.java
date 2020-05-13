package com.sqt.edu.nacos.service.impl;

import com.sqt.edu.common.base.JsonResult;
import com.sqt.edu.nacos.entity.TestTable;
import com.sqt.edu.nacos.mapper.TestTableMapper;
import com.sqt.edu.nacos.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-05-13 14:35
 */
@Slf4j
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestTableMapper testTableMapper;

    @Override
    public JsonResult createTx() {
        TestTable testTable = TestTable.builder()
                .name("wby")
                .email("123@qq.com")
                .build();
        testTableMapper.insert(testTable);
        int i = 1 / 0;
        return new JsonResult();
    }
}
