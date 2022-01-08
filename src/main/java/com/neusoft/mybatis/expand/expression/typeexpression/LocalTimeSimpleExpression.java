package com.neusoft.mybatis.expand.expression.typeexpression;

import com.neusoft.mybatis.expand.expression.SimpleExpression;

import java.time.LocalDate;
import java.time.LocalTime;

public class LocalTimeSimpleExpression extends SimpleExpression<LocalTime> {

    public LocalTimeSimpleExpression(String property) {
        super(property);
    }

    public LocalTimeSimpleExpression(String property, String operator) {
        super(property, operator);
    }
}
