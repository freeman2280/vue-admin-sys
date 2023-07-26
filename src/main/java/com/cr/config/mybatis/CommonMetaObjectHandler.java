package com.cr.config.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * mybatis_plus向数据库字段自动填充类，比如每次的修改和创建时间自动填充
 */
@Component
public class CommonMetaObjectHandler implements MetaObjectHandler {
    /**
     * 新增
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
//参数1：元数据对象
//参数2：属性名称（一定要和自己实体类字段名一致，如果有好几个表都要自动填充时间，则那几个表字段名也最好一致）
//参数3：类对象
//参数4：当前系统时间
        this.strictInsertFill(metaObject, "created", Date.class, new Date());
        this.strictUpdateFill(metaObject, "updated", Date.class, new Date());
    }
    /**
     * 修改
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updated", Date.class, new Date());
    }
}
