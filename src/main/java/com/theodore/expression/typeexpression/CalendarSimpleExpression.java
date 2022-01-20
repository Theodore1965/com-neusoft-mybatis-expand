package com.theodore.expression.typeexpression;

import com.theodore.expression.SimpleExpression;

import java.util.Calendar;

public class CalendarSimpleExpression extends SimpleExpression<Calendar> {

    public CalendarSimpleExpression(String property) {
        super(property);
    }

    public CalendarSimpleExpression(String property, String operator) {
        super(property, operator);
    }
}
