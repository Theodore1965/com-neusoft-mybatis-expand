package com.neusoft.mybatis.expand.expression.typeexpression;

import com.neusoft.mybatis.expand.expression.SimpleExpression;

import java.math.BigDecimal;
import java.util.Calendar;

public class CalendarSimpleExpression extends SimpleExpression<Calendar> {

    public CalendarSimpleExpression(String property) {
        super(property);
    }

    public CalendarSimpleExpression(String property, String operator) {
        super(property, operator);
    }
}
