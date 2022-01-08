package com.neusoft.mybatis.expand.utils;

import com.neusoft.mybatis.expand.enums.SystemCodeEnum;
import com.neusoft.mybatis.expand.global.BusinessException;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;

import java.util.Iterator;
import java.util.List;

public class BaseAssemblerUtils {
    public BaseAssemblerUtils() {
    }

    public static <T> T populate(Object src, Class<?> targetClass) {
        if (src != null) {
            try {
                Object obj = Class.forName(targetClass.getName()).newInstance();
                BeanUtils.copyProperties(src, obj);
                return (T) obj;
            } catch (Exception e) {
                throw new BusinessException(e);
            }
        } else {
            return null;
        }
    }

    /**
     * 将对象之间的属性映射转换
     *
     * @param src    源对象
     * @param target 复制前目标对象
     * @return 复制后目标对象
     */
    public static Object populate(Object src, Object target) {

        if (src == null || target == null) {
            return null;
        }
        try {
            BeanUtils.copyProperties(src, target);
        } catch (Exception e) {
            throw new BusinessException(e);
        }
        return target;

    }

    /**
     * 将对象集合间的属性映射转换
     *
     * @param src         源对象集合
     * @param target      复制前目标对象集合
     * @param targetClass 目标对象类型
     * @param <S>         泛型
     * @param <T>         泛型
     * @return 复制后目标对象集合
     */
    public static <S, T> List<T> populateList(List<S> src, List<T> target,
                                              Class<?> targetClass) {

        for (Object srcObj : src) {
            try {
                Object targetObj = targetClass.newInstance();
                target.add((T) populate(srcObj, targetObj));
            } catch (Exception e) {
                throw new BusinessException(e);
            }
        }
        return target;
    }


    /**
     * 将对象集合间的属性映射转换
     *
     * @param src         源对象集合
     * @param targetClass 目标对象类型
     * @param <S>         泛型
     * @param <T>         泛型
     * @return 复制后目标对象集合
     */
    public static <S, T> List<T> populateList(List<S> src, Class<?> targetClass) {
        List<T> target = Lists.newArrayList();
        for (Object srcObj : src) {
            try {
                target.add((T) populate(srcObj, targetClass));
            } catch (Exception e) {
                throw new BusinessException(e);
            }
        }
        return target;
    }
}
