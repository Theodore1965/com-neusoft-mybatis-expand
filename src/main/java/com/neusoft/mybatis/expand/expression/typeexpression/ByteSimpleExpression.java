package com.neusoft.mybatis.expand.expression.typeexpression;

import com.neusoft.mybatis.expand.expression.SimpleExpression;

public class ByteSimpleExpression extends SimpleExpression<Byte> {

    public ByteSimpleExpression(String property) {
        super(property);
    }

    public ByteSimpleExpression(String property, String operator) {
        super(property, operator);
    }
}
