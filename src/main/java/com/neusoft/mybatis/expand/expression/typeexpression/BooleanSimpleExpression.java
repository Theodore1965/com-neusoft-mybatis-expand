package com.neusoft.mybatis.expand.expression.typeexpression;

import com.neusoft.mybatis.expand.expression.SimpleExpression;

import java.math.BigInteger;

public class BooleanSimpleExpression extends SimpleExpression<Boolean> {

    public BooleanSimpleExpression(String property) {
        super(property);
    }

    public BooleanSimpleExpression(String property, String operator) {
        super(property, operator);
    }
}
