package com.sqt.edu.core.component;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * @Description: 自动填充字段 处理类
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2019-12-23 18:25
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("==========>start  insert field");
        Object gender = this.getFieldValByName("gender", metaObject);
        if (gender == null) {
            //避免使用metaObject.setValue()
            this.setFieldValByName("gender", 1, metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {

    }
}
