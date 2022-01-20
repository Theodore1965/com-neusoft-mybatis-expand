package com.theodore.expression.typeexpression;

import com.theodore.expression.SimpleExpression;

import java.math.BigInteger;

public class BigIntegerSimpleExpression extends SimpleExpression<BigInteger> {

    public BigIntegerSimpleExpression(String property) {
        super(property);
    }

    public BigIntegerSimpleExpression(String property, String operator) {
        super(property, operator);
    }
}
