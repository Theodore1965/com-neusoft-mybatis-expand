package com.neusoft.mybatis.expand;

import com.neusoft.mybatis.expand.expression.SimpleExpression;
import com.neusoft.mybatis.expand.utils.BaseAssemblerUtils;
import com.neusoft.mybatis.expand.utils.BeanContainer;
import com.neusoft.mybatis.expand.utils.feign.FeignUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.util.WebUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootTest
class ComNeusoftMybatisExpandApplicationTests {

    @Test
    void test1() throws Exception {
        Class<?> clz = Class.forName("com.neusoft.appointment.base.service.impl.BlackListServiceImpl");
        Object clzBlackListEntity = Class.forName("com.neusoft.appointment.base.entity.BlackListEntity").newInstance();

        Method method = ReflectionUtils.findMethod(clz, "saveBlackList", clzBlackListEntity.getClass());
        Object object = BeanContainer.getBean(clz);
        Field field_ = ReflectionUtils.findField(clzBlackListEntity.getClass(), "id");
        if (field_ != null) {
            ReflectionUtils.makeAccessible(field_);
            ReflectionUtils.setField(field_, clzBlackListEntity, 11);
        }
        ReflectionUtils.invokeMethod(method, object, clzBlackListEntity);
    }

    @Test
    void test2() throws Exception {
        FeignProxy feignProxy = FeignUtils.getService(FeignProxy.class, "http://localhost:8081");
//        Object str = feignProxy.getDoctor("C1A5C644FDB7476DE0531002000AC33D");
//        System.out.println(str);


        AptPatientQto qto = new AptPatientQto();
        qto.getActive().setValue(1);
        qto.getBirthday().setLowValue(new Date());
        qto.getBirthday().setHighValue(new Date());
        qto.getBirthday().setValue(new Date());
        qto.getBirthday().setOperator(SimpleExpression.eq);
        Object str_1 = feignProxy.getAptPatient(qto);

    }

    public static void main(String[] args) {
//        TestC t = new TestC();
//        t.setCode("1");
//        t.setName("测试");
//        TestTo to = BaseAssemblerUtils.populate(t, TestTo.class);
//        System.out.println(to.getCode());
//        System.out.println(to.getName());
//        Son1 son1 = new Son1();
//        son1.f();
//        son1.clone();
//
//        Son11 son11 = new Son11();
//        son11.f();
//        son11.clone();
    }
}
