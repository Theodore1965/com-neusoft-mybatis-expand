package com.neusoft.mybatis.expand.tenant;

public class TenantContextHolder {

    /**
     * 支持父子线程之间的数据传递
     */
    private static final ThreadLocal<String> THREAD_LOCAL_TENANT = new ThreadLocal<>();

    /**
     * TTL 设置租户ID<br/>
     * <b>谨慎使用此方法,避免嵌套调用。尽量使用 {@code TenantBroker} </b>
     *
     * @param tenantId 租户ID
     */
    public static void setTenantId(String tenantId) {
        THREAD_LOCAL_TENANT.set(tenantId);
    }

    /**
     * 获取TTL中的租户ID
     *
     * @return String
     */
    public static String getTenantId() {
        return THREAD_LOCAL_TENANT.get();
    }

    /**
     * 清除tenantId
     */
    public static void clear() {
        THREAD_LOCAL_TENANT.remove();
    }
}
