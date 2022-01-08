package com.neusoft.mybatis.expand.utils.feign;

import com.neusoft.mybatis.expand.expression.SimpleExpression;
import feign.QueryMapEncoder;
import feign.codec.EncodeException;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.*;

public class FeignQueryMapEncoder implements QueryMapEncoder {
    private Map<Class<?>, ObjectParamMetadata> classToMetadata;
    private static DateTimeFormatter dateFormatter;
    private static DateTimeFormatter timeFormatter;
    private static DateTimeFormatter dateTimeFormatter;
    private static SimpleDateFormat simpleDateFormatter;

    static {
        FeignQueryMapEncoder.dateFormatter = DateTimeFormatter.ISO_DATE;
        FeignQueryMapEncoder.timeFormatter = DateTimeFormatter.ISO_TIME;
        FeignQueryMapEncoder.dateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME;
        FeignQueryMapEncoder.simpleDateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
    }

    FeignQueryMapEncoder() {
        this.classToMetadata = new HashMap<>();
    }

    public Map<String, Object> encode(Object object) throws EncodeException {
        Map<String, Object> fieldNameToValue = new HashMap<>();
        if (object == null) {
            return fieldNameToValue;
        }
        try {
            ObjectParamMetadata metadata = this.getMetadata(object.getClass());
            for (Field field : metadata.objectFields) {
                Object value = field.get(object);
                if (value != null && value != object) {
                    if (SimpleExpression.class.isAssignableFrom(field.getType())) {
                        SimpleExpression<?> exp = (SimpleExpression<?>) value;
                        if (!exp.isValid()) {
                            continue;
                        }
                        String prop = exp.getProperty();
                        fieldNameToValue.put(field.getName() + ".property", prop);
                        String operator = exp.getOperator();
                        fieldNameToValue.put(field.getName() + ".operator", operator);
                        Object val = this.convertType(exp.getValue());
                        fieldNameToValue.put(field.getName() + ".value", val);
                        if (StringUtils.equals(operator, SimpleExpression.between) || StringUtils.equals(operator, SimpleExpression.notBetween)) {
                            Object hiVal = this.convertType(exp.getHighValue());
                            fieldNameToValue.put(field.getName() + ".highValue", hiVal);
                            Object loVal = this.convertType(exp.getLowValue());
                            fieldNameToValue.put(field.getName() + ".lowValue", loVal);
                        }
                        if (!StringUtils.equals(operator, SimpleExpression.in) && !StringUtils.equals(operator, SimpleExpression.notIn)) {
                            continue;
                        }
                        List<?> vals = exp.getValues();
                        for (int size = vals.size(), i = 0; i < size; ++i) {
                            fieldNameToValue.put(field.getName() + ".values[" + i + "]", this.convertType(vals.get(i)));
                        }
                    } else {
                        fieldNameToValue.put(field.getName(), this.convertType(value));
                    }
                }
            }
            return fieldNameToValue;
        } catch (IllegalAccessException e) {
            throw new EncodeException("Failure encoding object into query map", e);
        }
    }

    private ObjectParamMetadata getMetadata(Class<?> objectType) {
        ObjectParamMetadata metadata = this.classToMetadata.get(objectType);
        if (metadata == null) {
            metadata = metadata.parseObjectType(objectType);
            this.classToMetadata.put(objectType, metadata);
        }
        return metadata;
    }

    private Object convertType(Object value) {
        if (value instanceof LocalDate) {
            return FeignQueryMapEncoder.dateFormatter.format((TemporalAccessor) value);
        }
        if (value instanceof LocalTime) {
            return FeignQueryMapEncoder.timeFormatter.format((TemporalAccessor) value);
        }
        if (value instanceof LocalDateTime) {
            return FeignQueryMapEncoder.dateTimeFormatter.format((TemporalAccessor) value);
        }
        if (value instanceof Date) {
            return FeignQueryMapEncoder.simpleDateFormatter.format((Date) value);
        }
        return value;
    }

    private static class ObjectParamMetadata {
        private List<Field> objectFields;

        private ObjectParamMetadata(List<Field> objectFields) {
            this.objectFields = Collections.unmodifiableList(objectFields);
        }

        private static ObjectParamMetadata parseObjectType(Class<?> type) {
            List<Field> fields = new ArrayList<Field>();
            Field[] declaredFields;
            int fieldLength = (declaredFields = type.getDeclaredFields()).length;
            for (int i = 0; i < fieldLength; ++i) {
                Field field = declaredFields[i];
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                fields.add(field);
            }
            return new ObjectParamMetadata(fields);
        }
    }
}
