package com.theodore.expression.typeexpression;

import com.theodore.expression.SimpleExpression;

import java.time.LocalTime;

public class LocalTimeSimpleExpression extends SimpleExpression<LocalTime> {

    public LocalTimeSimpleExpression(String property) {
        super(property);
    }

    public LocalTimeSimpleExpression(String property, String operator) {
        super(property, operator);
    }
}
