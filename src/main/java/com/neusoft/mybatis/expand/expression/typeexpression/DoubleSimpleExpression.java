package com.neusoft.mybatis.expand.expression.typeexpression;

import com.neusoft.mybatis.expand.expression.SimpleExpression;

import java.util.Calendar;

public class DoubleSimpleExpression extends SimpleExpression<Double> {

    public DoubleSimpleExpression(String property) {
        super(property);
    }

    public DoubleSimpleExpression(String property, String operator) {
        super(property, operator);
    }
}
