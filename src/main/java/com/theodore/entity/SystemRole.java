package com.theodore.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.util.Date;
import java.io.Serializable;

/**
 * (SystemRole)实体类
 *
 * @author yufeng
 * @since 2022-01-08 15:47:50
 */
public class SystemRole implements Serializable {
    private static final long serialVersionUID = 286456769888732140L;
    /**
    * 标识
    */
    @TableId(type = IdType.NONE)
    private String id;
    /**
    * 名称
    */
    private String name;
    /**
    * 院区编码
    */
    private String hospitalCode;
    /**
    * 启用状态
    */
    private Integer enabled;
    /**
    * 创建人
    */
    private String createMan;
    /**
    * 创建时间
    */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    /**
    * 修改人
    */
    private String updateMan;
    /**
    * 修改时间
    */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

        
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
        
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
        
    public String getHospitalCode() {
        return hospitalCode;
    }

    public void setHospitalCode(String hospitalCode) {
        this.hospitalCode = hospitalCode;
    }
        
    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }
        
    public String getCreateMan() {
        return createMan;
    }

    public void setCreateMan(String createMan) {
        this.createMan = createMan;
    }
        
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
        
    public String getUpdateMan() {
        return updateMan;
    }

    public void setUpdateMan(String updateMan) {
        this.updateMan = updateMan;
    }
        
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString(){
        return "SystemRole {" +
            "id : " + id + ", " +
            "name : " + name + ", " +
            "hospitalCode : " + hospitalCode + ", " +
            "enabled : " + enabled + ", " +
            "createMan : " + createMan + ", " +
            "createTime : " + createTime + ", " +
            "updateMan : " + updateMan + ", " +
            "updateTime : " + updateTime + ", " +
        '}';
    }
}