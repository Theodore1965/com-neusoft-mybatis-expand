package com.neusoft.mybatis.expand.expression.typeexpression;

import com.neusoft.mybatis.expand.expression.SimpleExpression;

public class IntegerSimpleExpression extends SimpleExpression<Integer> {

    public IntegerSimpleExpression(String property) {
        super(property);
    }

    public IntegerSimpleExpression(String property, String operator) {
        super(property, operator);
    }
}
