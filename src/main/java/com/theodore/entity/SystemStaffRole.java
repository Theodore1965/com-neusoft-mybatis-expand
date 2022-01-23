package com.theodore.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.util.Date;
import java.io.Serializable;

/**
 * (SystemStaffRole)实体类
 *
 * @author yufeng
 * @since 2022-01-08 15:52:21
 */
public class SystemStaffRole implements Serializable {
    private static final long serialVersionUID = -97789415604627886L;
    /**
    * 标识
    */
    @TableId(type = IdType.ASSIGN_ID)
    private Integer id;
    /**
    * 员工标识
    */
    private String staffId;
    /**
    * 角色标识
    */
    private Integer roleId;
    /**
    * 院区编码
    */
    private String hospitalCode;
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

        
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
        
    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }
        
    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
        
    public String getHospitalCode() {
        return hospitalCode;
    }

    public void setHospitalCode(String hospitalCode) {
        this.hospitalCode = hospitalCode;
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
        return "SystemStaffRole {" +
            "id : " + id + ", " +
            "staffId : " + staffId + ", " +
            "roleId : " + roleId + ", " +
            "hospitalCode : " + hospitalCode + ", " +
            "createMan : " + createMan + ", " +
            "createTime : " + createTime + ", " +
            "updateMan : " + updateMan + ", " +
            "updateTime : " + updateTime + ", " +
        '}';
    }
}