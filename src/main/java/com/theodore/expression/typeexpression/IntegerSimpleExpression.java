package com.theodore.expression.typeexpression;

import com.theodore.expression.SimpleExpression;

public class IntegerSimpleExpression extends SimpleExpression<Integer> {

    public IntegerSimpleExpression(String property) {
        super(property);
    }

    public IntegerSimpleExpression(String property, String operator) {
        super(property, operator);
    }
}
