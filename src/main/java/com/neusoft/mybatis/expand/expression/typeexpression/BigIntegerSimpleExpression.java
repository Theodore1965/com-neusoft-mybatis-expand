package com.neusoft.mybatis.expand.expression.typeexpression;

import com.neusoft.mybatis.expand.expression.SimpleExpression;

import java.math.BigDecimal;
import java.math.BigInteger;

public class BigIntegerSimpleExpression extends SimpleExpression<BigInteger> {

    public BigIntegerSimpleExpression(String property) {
        super(property);
    }

    public BigIntegerSimpleExpression(String property, String operator) {
        super(property, operator);
    }
}
