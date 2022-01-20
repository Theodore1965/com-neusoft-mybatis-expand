package com.theodore.expression.typeexpression;

import com.theodore.expression.SimpleExpression;

public class BooleanSimpleExpression extends SimpleExpression<Boolean> {

    public BooleanSimpleExpression(String property) {
        super(property);
    }

    public BooleanSimpleExpression(String property, String operator) {
        super(property, operator);
    }
}
