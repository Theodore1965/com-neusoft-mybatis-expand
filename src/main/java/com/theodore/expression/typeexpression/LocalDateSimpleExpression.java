package com.theodore.expression.typeexpression;

import com.theodore.expression.SimpleExpression;

import java.time.LocalDate;

public class LocalDateSimpleExpression extends SimpleExpression<LocalDate> {

    public LocalDateSimpleExpression(String property) {
        super(property);
    }

    public LocalDateSimpleExpression(String property, String operator) {
        super(property, operator);
    }
}
