package com.neusoft.mybatis.expand.expression.typeexpression;

import com.neusoft.mybatis.expand.expression.SimpleExpression;

public class ShortSimpleExpression extends SimpleExpression<Short> {

    public ShortSimpleExpression(String property) {
        super(property);
    }

    public ShortSimpleExpression(String property, String operator) {
        super(property, operator);
    }
}
