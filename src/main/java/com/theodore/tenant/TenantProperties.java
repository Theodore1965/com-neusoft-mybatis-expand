package com.theodore.tenant;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@ConfigurationProperties(prefix = "mate.tenant")
public class TenantProperties {
    /**
     * 是否开启租户模式
     */
    private Boolean enable = true;

    /**
     * 需要排除的多租户的表
     */
    private List<String> ignoreTables = new ArrayList<>();

    /**
     * 多租户字段名称
     */
    private String column = "hospital_dist";

    /**
     * 排除不进行租户隔离的sql
     * 样例全路径：vip.mate.system.mapper.UserMapper.findList
     */
    private List<String> ignoreSqls = new ArrayList<>();

    /**
     * 默认租户标识
     */
    private String defaultTenantId;

    /**
     * 请求头租户Key
     */
    private String tenantHeaderKey;

    /**
     * 如果前台传入租户标识字段，是否使用后台覆盖
     */
    private boolean filterTenantKey;

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public List<String> getIgnoreTables() {
        return ignoreTables;
    }

    public void setIgnoreTables(List<String> ignoreTables) {
        this.ignoreTables = ignoreTables;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public List<String> getIgnoreSqls() {
        return ignoreSqls;
    }

    public void setIgnoreSqls(List<String> ignoreSqls) {
        this.ignoreSqls = ignoreSqls;
    }

    public String getDefaultTenantId() {
        return defaultTenantId;
    }

    public void setDefaultTenantId(String defaultTenantId) {
        this.defaultTenantId = defaultTenantId;
    }

    public String getTenantHeaderKey() {
        return tenantHeaderKey;
    }

    public void setTenantHeaderKey(String tenantHeaderKey) {
        this.tenantHeaderKey = tenantHeaderKey;
    }

    public boolean isFilterTenantKey() {
        return filterTenantKey;
    }

    public void setFilterTenantKey(boolean filterTenantKey) {
        this.filterTenantKey = filterTenantKey;
    }
}
