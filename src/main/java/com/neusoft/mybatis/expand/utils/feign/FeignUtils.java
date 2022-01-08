package com.neusoft.mybatis.expand.utils.feign;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.neusoft.mybatis.expand.utils.BeanContainer;
import feign.*;
import feign.form.FormEncoder;
import feign.httpclient.ApacheHttpClient;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.util.Assert;

import java.util.Properties;

public class FeignUtils {
    private static ObjectMapper objectMapper;
    private static Feign.Builder builder1;

    static {
        FeignUtils.objectMapper = new ObjectMapper().registerModules(new Module[] {new JavaTimeModule() }).configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        FeignUtils.builder1 = Feign.builder()
                // 不重试
                .retryer(Retryer.NEVER_RETRY)
                .client(new ApacheHttpClient())
                // 超时时间
                .options(new Request.Options(10000, 3600000))
                // 查询参数Encode
                .queryMapEncoder(new FeignQueryMapEncoder())
                .encoder(new FormEncoder(new JacksonEncoder(FeignUtils.objectMapper)))
                .decoder(new JacksonDecoder(FeignUtils.objectMapper))
                .errorDecoder(new FeignErrorDecoder());
    }

    public static synchronized <T> T getService( Class<T> serviceClass,  String host) {
        Assert.notNull(serviceClass, "serviceClass不能为空。");
        Assert.hasText(host, "请求地址不能为空。");
        return FeignUtils.builder1.target(serviceClass, host);
    }
}
