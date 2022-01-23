package com.theodore.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.theodore.security.SecurityUtils;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;

public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
        this.strictInsertFill(metaObject, "updateTime", Date.class, new Date());
        this.strictInsertFill(metaObject, "createMan", String.class, SecurityUtils.getCurrentUsername().get());
        this.strictInsertFill(metaObject, "updateMan", String.class, SecurityUtils.getCurrentUsername().get());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date());
        this.strictInsertFill(metaObject, "updateMan", String.class, SecurityUtils.getCurrentUsername().get());
    }
}
