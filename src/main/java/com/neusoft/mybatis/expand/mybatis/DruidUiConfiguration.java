package com.neusoft.mybatis.expand.mybatis;

import com.alibaba.druid.support.http.StatViewServlet;
import com.neusoft.mybatis.expand.tenant.TenantProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(DruidUiProperties.class)
public class DruidUiConfiguration {

    @Autowired
    private DruidUiProperties druidUiProperties;

    @Bean
    @ConditionalOnMissingBean(ServletRegistrationBean.class)
    public ServletRegistrationBean servletRegistrationBean() {
        // 创建servlet注册实体
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),
                "/druid/*");
        // 设置ip白名单
        servletRegistrationBean.addInitParameter("allow", "");
        // 设置ip黑名单，如果allow与deny共同存在时,deny优先于allow
        // servletRegistrationBean.addInitParameter("deny", "****");
        // 设置控制台管理用户
        servletRegistrationBean.addInitParameter("loginUsername", druidUiProperties.getLoginUsername());
        servletRegistrationBean.addInitParameter("loginPassword", druidUiProperties.getLoginPassword());
        // 是否可以重置数据
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }
}