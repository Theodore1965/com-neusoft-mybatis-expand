package com.theodore.expression.typeexpression;

import com.theodore.expression.SimpleExpression;

public class FloatSimpleExpression extends SimpleExpression<Float> {

    public FloatSimpleExpression(String property) {
        super(property);
    }

    public FloatSimpleExpression(String property, String operator) {
        super(property, operator);
    }
}
