package com.theodore.expression;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.theodore.global.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class SimpleExpressionWrapper {
    private static Map<Class<?>, ObjectParamMetadata> classToMetadata = new HashMap<Class<?>, ObjectParamMetadata>();

    SimpleExpressionWrapper() {
    }

    public static QueryWrapper<T> getWrapper(Object object) {
        QueryWrapper<T> queryWrapper = new QueryWrapper();
        if (object == null) {
            return queryWrapper;
        }
        try {
            ObjectParamMetadata metadata = getMetadata(object.getClass());
            List<String> sortby = new ArrayList<>();
            String sortorder = null;
            for (Field field : metadata.objectFields) {
                Object value = field.get(object);
                if (value == null || value == object) {
                    continue;
                }
                if (SimpleExpression.class.isAssignableFrom(field.getType())) {
                    SimpleExpression<?> exp = (SimpleExpression<?>) value;
                    if (!exp.isValid())
                        continue;
                    queryWrapper = exp.getWrapper(queryWrapper);
                } else if (String.class.isAssignableFrom(field.getType())){
                    if ("sortorder".equals(field.getName())) {
                        sortorder = (String) value;
                    }
                } else if (Collection.class.isAssignableFrom(field.getType())){
                    if ("sortby".equals(field.getName())) {
                        List<String> values = (List<String>) value;
                        // 转为大写下划线
                        sortby = values.stream().map(p -> {
                            return HumpCharUtil.camelToUnderline(p);
                        }).collect(Collectors.toList());
                    }
                }
            }
            // 排序
            if (sortby != null && !sortby.isEmpty() && StringUtils.isNotBlank(sortorder)) {
                if ("ASC".equals(sortorder.toUpperCase())) {
                    queryWrapper.orderByAsc(sortby.toArray(new String[]{}));
                } else if ("DESC".equals(sortorder.toUpperCase())) {
                    queryWrapper.orderByDesc(sortby.toArray(new String[]{}));
                }
            }
            return queryWrapper;
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    private static ObjectParamMetadata getMetadata(Class<?> objectType) {
        ObjectParamMetadata metadata = classToMetadata.get(objectType);
        if (metadata == null) {
            metadata = ObjectParamMetadata.parseObjectType(objectType);
            classToMetadata.put(objectType, metadata);
        }
        return metadata;
    }

    private static class ObjectParamMetadata {
        private final List<Field> objectFields;

        private ObjectParamMetadata(List<Field> objectFields) {
            this.objectFields = Collections.unmodifiableList(objectFields);
        }

        private static ObjectParamMetadata parseObjectType(Class<?> type) {
            ArrayList<Field> fields = new ArrayList<Field>();
            // 父类加子类的所有字段
            Field[] fieldArray = getSuperClassFields(new Field[]{}, type);
            int n = fieldArray.length;
            int n2 = 0;
            while (n2 < n) {
                Field field = fieldArray[n2];
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                fields.add(field);
                ++n2;
            }

            return new ObjectParamMetadata(fields);
        }
    }

    private static Field[] getSuperClassFields(Field[] tableFields, Class<?> clazz) {
        // 当前类
        Field[] tableSuperFields = clazz.getDeclaredFields();

        Class<?> superClazz = clazz.getSuperclass();

        Field[] c = new Field[tableFields.length + tableSuperFields.length];
        System.arraycopy(tableFields, 0, c, 0, tableFields.length);
        System.arraycopy(tableSuperFields, 0, c, tableFields.length, tableSuperFields.length);
        // 如果父类不是Object
        if (!superClazz.equals(Object.class)) {
            c = getSuperClassFields(c, superClazz);
        }
        return c;
    }
}
