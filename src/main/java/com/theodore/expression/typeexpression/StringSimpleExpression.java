package com.theodore.expression.typeexpression;

import com.theodore.expression.SimpleExpression;

public class StringSimpleExpression extends SimpleExpression<String> {

    public StringSimpleExpression(String property) {
        super(property);
    }

    public StringSimpleExpression(String property, String operator) {
        super(property, operator);
    }
}
