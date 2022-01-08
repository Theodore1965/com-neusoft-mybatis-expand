package com.neusoft.mybatis.expand.expression.typeexpression;

import com.neusoft.mybatis.expand.expression.SimpleExpression;

public class StringSimpleExpression extends SimpleExpression<String> {

    public StringSimpleExpression(String property) {
        super(property);
    }

    public StringSimpleExpression(String property, String operator) {
        super(property, operator);
    }
}
