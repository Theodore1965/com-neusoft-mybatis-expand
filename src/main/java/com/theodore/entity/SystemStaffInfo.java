package com.theodore.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.util.Date;
import java.io.Serializable;

/**
 * 用户信息表(SystemStaffInfo)实体类
 *
 * @author yufeng
 * @since 2022-01-08 15:04:24
 */
public class SystemStaffInfo implements Serializable {
    private static final long serialVersionUID = -31495963996889673L;
    /**
    * 用户ID
    */
    @TableId(type = IdType.NONE)
    private String staffId;
    /**
    * 用户名
    */
    private String staffName;
    /**
    * 昵称
    */
    private String staffNickname;
    /**
    * 密码
    */
    private String staffPassword;
    /**
    * 角色权限组
    */
    private String roleId;
    /**
    * 角色名(冗余)
    */
    private String roleName;
    /**
    * 所属科室编码
    */
    private String deptId;
    /**
    * 工作类别
    */
    private String worktype;
    /**
    * 职务类别
    */
    private String jobtype;
    /**
    * 是否禁用(0无权限，1有权限)
    */
    private boolean enabled;
    /**
    * 拼音码
    */
    private String pinyin;
    /**
    * 排序号
    */
    private Integer sortNo;
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
    /**
    * 证书类别
    */
    private String diplomaType;

        
    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }
        
    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }
        
    public String getStaffNickname() {
        return staffNickname;
    }

    public void setStaffNickname(String staffNickname) {
        this.staffNickname = staffNickname;
    }
        
    public String getStaffPassword() {
        return staffPassword;
    }

    public void setStaffPassword(String staffPassword) {
        this.staffPassword = staffPassword;
    }
        
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
        
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
        
    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }
        
    public String getWorktype() {
        return worktype;
    }

    public void setWorktype(String worktype) {
        this.worktype = worktype;
    }
        
    public String getJobtype() {
        return jobtype;
    }

    public void setJobtype(String jobtype) {
        this.jobtype = jobtype;
    }
        
    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
        
    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }
        
    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
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
        
    public String getDiplomaType() {
        return diplomaType;
    }

    public void setDiplomaType(String diplomaType) {
        this.diplomaType = diplomaType;
    }

    @Override
    public String toString(){
        return "SystemStaffInfo {" +
            "staffId : " + staffId + ", " +
            "staffName : " + staffName + ", " +
            "staffNickname : " + staffNickname + ", " +
            "staffPassword : " + staffPassword + ", " +
            "roleId : " + roleId + ", " +
            "roleName : " + roleName + ", " +
            "deptId : " + deptId + ", " +
            "worktype : " + worktype + ", " +
            "jobtype : " + jobtype + ", " +
            "enabled : " + enabled + ", " +
            "pinyin : " + pinyin + ", " +
            "sortNo : " + sortNo + ", " +
            "createMan : " + createMan + ", " +
            "createTime : " + createTime + ", " +
            "updateMan : " + updateMan + ", " +
            "updateTime : " + updateTime + ", " +
            "diplomaType : " + diplomaType + ", " +
        '}';
    }
}