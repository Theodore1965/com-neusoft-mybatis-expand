package com.neusoft.mybatis.expand.expression.typeexpression;

import com.neusoft.mybatis.expand.expression.SimpleExpression;

import java.math.BigDecimal;
import java.util.Date;

public class BigDecimalSimpleExpression extends SimpleExpression<BigDecimal> {

    public BigDecimalSimpleExpression(String property) {
        super(property);
    }

    public BigDecimalSimpleExpression(String property, String operator) {
        super(property, operator);
    }
}
