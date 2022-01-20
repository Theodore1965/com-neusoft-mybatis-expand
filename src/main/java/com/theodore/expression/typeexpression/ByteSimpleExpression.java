package com.theodore.expression.typeexpression;

import com.theodore.expression.SimpleExpression;

public class ByteSimpleExpression extends SimpleExpression<Byte> {

    public ByteSimpleExpression(String property) {
        super(property);
    }

    public ByteSimpleExpression(String property, String operator) {
        super(property, operator);
    }
}
