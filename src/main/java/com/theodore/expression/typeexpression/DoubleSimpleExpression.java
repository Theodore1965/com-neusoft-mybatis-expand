package com.theodore.expression.typeexpression;

import com.theodore.expression.SimpleExpression;

public class DoubleSimpleExpression extends SimpleExpression<Double> {

    public DoubleSimpleExpression(String property) {
        super(property);
    }

    public DoubleSimpleExpression(String property, String operator) {
        super(property, operator);
    }
}
