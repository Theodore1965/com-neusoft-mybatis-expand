package com.neusoft.mybatis.expand.expression.typeexpression;

import com.neusoft.mybatis.expand.expression.SimpleExpression;

public class FloatSimpleExpression extends SimpleExpression<Float> {

    public FloatSimpleExpression(String property) {
        super(property);
    }

    public FloatSimpleExpression(String property, String operator) {
        super(property, operator);
    }
}
