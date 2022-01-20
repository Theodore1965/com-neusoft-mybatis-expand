package com.theodore.mybatis;

import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;

public class MyPaginationInnerInterceptor extends PaginationInnerInterceptor {

    /**
     * Mybatis plus 提供分页综合方法
     * @param sql
     * @return
     */
    public String autoCountSql(String sql) {
        return super.autoCountSql(true, sql);
    }
}
