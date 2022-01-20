package com.theodore.expression.typeexpression;

import com.theodore.expression.SimpleExpression;

import java.math.BigDecimal;

public class BigDecimalSimpleExpression extends SimpleExpression<BigDecimal> {

    public BigDecimalSimpleExpression(String property) {
        super(property);
    }

    public BigDecimalSimpleExpression(String property, String operator) {
        super(property, operator);
    }
}
