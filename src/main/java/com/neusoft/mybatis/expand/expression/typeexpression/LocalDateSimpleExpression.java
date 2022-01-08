package com.neusoft.mybatis.expand.expression.typeexpression;

import com.neusoft.mybatis.expand.expression.SimpleExpression;

import java.time.LocalDate;

public class LocalDateSimpleExpression extends SimpleExpression<LocalDate> {

    public LocalDateSimpleExpression(String property) {
        super(property);
    }

    public LocalDateSimpleExpression(String property, String operator) {
        super(property, operator);
    }
}
