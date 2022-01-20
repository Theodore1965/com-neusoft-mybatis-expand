package com.theodore.expression.typeexpression;

import com.theodore.expression.SimpleExpression;

import java.util.Date;

public class DateSimpleExpression extends SimpleExpression<Date> {

    public DateSimpleExpression(String property) {
        super(property);
    }

    public DateSimpleExpression(String property, String operator) {
        super(property, operator);
    }
}
