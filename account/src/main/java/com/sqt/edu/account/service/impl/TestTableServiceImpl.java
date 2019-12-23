package com.sqt.edu.account.service.impl;

import com.sqt.edu.account.entity.TestTable;
import com.sqt.edu.account.mapper.TestTableMapper;
import com.sqt.edu.account.service.TestTableService;
import com.sqt.edu.core.base.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.ServiceMode;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2019-12-20 16:49
 */
@Slf4j
@Service
public class TestTableServiceImpl implements TestTableService {

    @Autowired
    private TestTableMapper testTableMapper;

    @Override
    public JsonResult inteceptor() {
        TestTable testTable = new TestTable();
        testTableMapper.insert(testTable);
        return new JsonResult();
    }
}
