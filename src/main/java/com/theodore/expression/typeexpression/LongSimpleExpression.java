package com.theodore.expression.typeexpression;

import com.theodore.expression.SimpleExpression;

public class LongSimpleExpression extends SimpleExpression<Long> {

    public LongSimpleExpression(String property) {
        super(property);
    }

    public LongSimpleExpression(String property, String operator) {
        super(property, operator);
    }
}
