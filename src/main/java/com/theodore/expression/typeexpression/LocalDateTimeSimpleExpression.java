package com.theodore.expression.typeexpression;

import com.theodore.expression.SimpleExpression;

import java.time.LocalDateTime;

public class LocalDateTimeSimpleExpression extends SimpleExpression<LocalDateTime> {

    public LocalDateTimeSimpleExpression(String property) {
        super(property);
    }

    public LocalDateTimeSimpleExpression(String property, String operator) {
        super(property, operator);
    }
}
