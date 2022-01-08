package com.neusoft.mybatis.expand.expression.typeexpression;

import com.neusoft.mybatis.expand.expression.SimpleExpression;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class LocalDateTimeSimpleExpression extends SimpleExpression<LocalDateTime> {

    public LocalDateTimeSimpleExpression(String property) {
        super(property);
    }

    public LocalDateTimeSimpleExpression(String property, String operator) {
        super(property, operator);
    }
}
