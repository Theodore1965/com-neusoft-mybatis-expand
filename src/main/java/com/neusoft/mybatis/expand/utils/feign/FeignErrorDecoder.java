package com.neusoft.mybatis.expand.utils.feign;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neusoft.mybatis.expand.global.BusinessException;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;

import java.io.IOException;
import java.util.Collection;


public class FeignErrorDecoder implements ErrorDecoder {
    public Exception decode(String methodKey, Response response) {
        String body = "";
        try {
            if (response.body() != null) {
                Collection<String> headers = response.headers().get("Server");
                if (headers != null && !headers.isEmpty() && StringUtils.contains((CharSequence) headers.iterator().next(), (CharSequence) "kong") && response.status() == 404) {
                    return new BusinessException("\u7f51\u5173\u5f02\u5e38\uff1a\u8bf7\u6c42\u7684URL\u8d44\u6e90(" + response.request().url() + ")\u4e0d\u5b58\u5728\uff01");
                }
                if (response.status() == 404) {
                    return new BusinessException("404");
                }
                body = Util.toString(response.body().asReader());

                System.out.println(body);
//                ObjectMapper mapper = new ObjectMapper();
//                Errors errors = mapper.readValue(body, Errors.class);
//
//
//                String messages = "";
//                String[] messages2;
//                for (int length = (messages2 = errors.getMessages()).length, i = 0; i < length; ++i) {
//                    String message = messages2[i];
//                    messages = String.valueOf(messages) + message + "\n";
//                }
//                if (response.status() == 500) {
//                    return new BusinessException(messages);
//                }
                return new BusinessException(body);
            }
        } catch (IOException e) {
            throw new BusinessException(body);
        }
        String message2 = String.format("status %s reading %s", response.status(), methodKey);
        return new BusinessException(message2);
    }
}
