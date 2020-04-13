package com.sqt.edu.core.component;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.sqt.edu.common.utils.RequestHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Description: 自动填充字段 处理类
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2019-12-23 18:25
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    /**
     * create_time字段
     */
    private static String CREATE_TIME_FIELD = "createTime";
    /**
     * update_time 字段
     */
    private static String UPDATE_TIME_FIELD = "updateTime";
    /**
     * create_user 字段
     */
    private static String CREATE_USER_FIELD = "createUser";
    /**
     * update_user 字段
     */
    private static String UPDATE_USER_FIELD = "updateUser";

    @Override
    public void insertFill(MetaObject metaObject) {
        checkFieldInsert(CREATE_TIME_FIELD, metaObject);
        checkFieldInsert(CREATE_USER_FIELD, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName(UPDATE_TIME_FIELD,new Date(),metaObject);
        this.setFieldValByName(UPDATE_USER_FIELD,RequestHelper.getUserId(),metaObject);

    }

    /**
     * 新增时 字段校验
     *
     * @param fieldName
     * @param metaObject
     */
    private void checkFieldInsert(String fieldName, MetaObject metaObject) {
        boolean bol = metaObject.hasSetter(fieldName);
        //拿到字段属性的值
        Object fieldValue = getFieldValByName(fieldName, metaObject);
        Class<?> fieldClass = metaObject.getGetterType(fieldName);
        //插入时填充创建时间 fieldName是属性名
        if (bol) {
            if (fieldClass.equals(Date.class) && fieldValue == null) {
                setFieldValByName(fieldName, new Date(), metaObject);
            }
            if (fieldClass.equals(Long.class) && fieldValue == null){
                setFieldValByName(fieldName, RequestHelper.getUserId(), metaObject);
            }
        }
    }


}
