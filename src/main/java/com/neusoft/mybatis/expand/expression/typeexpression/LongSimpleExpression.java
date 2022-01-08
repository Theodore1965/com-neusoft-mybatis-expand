package com.neusoft.mybatis.expand.expression.typeexpression;

import com.neusoft.mybatis.expand.expression.SimpleExpression;

import java.time.LocalTime;

public class LongSimpleExpression extends SimpleExpression<Long> {

    public LongSimpleExpression(String property) {
        super(property);
    }

    public LongSimpleExpression(String property, String operator) {
        super(property, operator);
    }
}
