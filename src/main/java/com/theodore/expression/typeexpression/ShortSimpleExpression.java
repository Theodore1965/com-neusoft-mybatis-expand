package com.theodore.expression.typeexpression;

import com.theodore.expression.SimpleExpression;

public class ShortSimpleExpression extends SimpleExpression<Short> {

    public ShortSimpleExpression(String property) {
        super(property);
    }

    public ShortSimpleExpression(String property, String operator) {
        super(property, operator);
    }
}
