package com.theodore.mybatis;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class BaseEntity implements Serializable {

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("创建人")
    private String createMan;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty("修改人")
    private String updateMan;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty("修改时间")
    private Date updateTime;
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("创建时间")
    private Date createTime;

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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

    public String getCreateMan() {
        return createMan;
    }

    public void setCreateMan(String createMan) {
        this.createMan = createMan;
    }
}
