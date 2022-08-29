package com.qiqi.mybatisplus.MetaObjectHandler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;

public class MyMateObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        //获取需要被填充字段的值
        Object name = getFieldValByName("name", metaObject);
        if (name==null){
            System.out.println("*******插入操作满足填充条件********");
            setFieldValByName("name","hello32",metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //获取需要被填充字段的值
        Object name = getFieldValByName("name", metaObject);
        if (name==null){
            System.out.println("*******修改操作满足填充条件********");
            setFieldValByName("name","qwer",metaObject);
        }
        //this.strictUpdateFill(metaObject,"sasc", LocalDateTime.class,LocalDateTime.now());
    }
}
