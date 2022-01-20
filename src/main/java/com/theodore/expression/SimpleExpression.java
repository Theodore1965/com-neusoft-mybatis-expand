package com.theodore.expression;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SimpleExpression<T extends Comparable<? super T>> implements Serializable {

    /**
     * 等于
     */
    public static final String eq = "eq";
    /**
     * 不等于
     */
    public static final String ne = "ne";
    /**
     * 小于
     */
    public static final String lt = "lt";
    /**
     * 小于等于
     */
    public static final String le = "le";
    /**
     * 大于
     */
    public static final String gt = "gt";
    /**
     * 大于等于
     */
    public static final String ge = "ge";
    public static final String isNull = "isNull";
    public static final String isNotNull = "isNotNull";
    public static final String in = "in";
    public static final String notIn = "notIn";
    public static final String between = "between";
    public static final String notBetween = "notBetween";
    public static final String like = "like";
    public static final String notLike = "notLike";
    private String property;
    private String operator;
    private String operatorRelation = "and";
    private T value = null;
    private T lowValue = null;
    private T highValue = null;
    private List<T> values = new ArrayList<T>();
    private boolean enabled = true;

    public SimpleExpression() {
        this.setOperator(eq);
    }

    public SimpleExpression(String property) {
        this(property, eq);
    }

    public SimpleExpression(String property, String operator) {
        Assert.notNull(property, "参数property不能为空！");
        Assert.notNull(operator, "参数operator不能为空！");
        Assert.isTrue(this.isValidOperator(operator), "参数operator不是有效的操作符！");
        this.setProperty(property);
        this.setOperator(operator);
    }

    public String getProperty() {
        return this.property;
    }

    /**
     * 驼峰命名转换成下大写加下划线，mybatis中QueryWrapper使用
     *
     * @return
     */
    public String getHumpProperty() {
        return HumpCharUtil.camelToUnderline(this.property);
    }

    public void setProperty(String property) {
        Assert.notNull(property, "参数property不能为空！");
        this.property = property;
    }

    public String getOperator() {
        return this.operator;
    }

    public void setOperator(String operator) {
        Assert.notNull(operator, "参数operator不能为空！");
        Assert.isTrue(this.isValidOperator(operator), "参数operator不是有效的操作符！");
        this.operator = operator;
    }

    public T getValue() {
        return this.value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public T getLowValue() {
        return this.lowValue;
    }

    public void setLowValue(T lowValue) {
        this.lowValue = lowValue;
    }

    public T getHighValue() {
        return this.highValue;
    }

    public void setHighValue(T highValue) {
        this.highValue = highValue;
    }

    public List<T> getValues() {
        return this.values;
    }

    public void setValues(List<T> values) {
        this.values = values;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public QueryWrapper getWrapper(QueryWrapper queryWrapper) {

        Assert.notNull(queryWrapper, "参数QueryWrapper不能为空！");
        Assert.isTrue(this.isValidOperator(this.getOperator()), "operator为空或不是有效的操作符！");
        if (!this.isValid()) {
            return null;
        }
        switch (getOperator()) {
            case "isNull":
                queryWrapper.isNull(getHumpProperty());
                break;
            case "isNotNull":
                queryWrapper.isNotNull(getHumpProperty());
                break;
            case "eq":
                queryWrapper.eq(getHumpProperty(), getValue());
                break;
            case "ne":
                queryWrapper.ne(getHumpProperty(), getValue());
                break;
            case "le":
                queryWrapper.le(getHumpProperty(), getValue());
                break;
            case "lt":
                queryWrapper.lt(getHumpProperty(), getValue());
                break;
            case "ge":
                queryWrapper.ge(getHumpProperty(), getValue());
                break;
            case "gt":
                queryWrapper.gt(getHumpProperty(), getValue());
                break;
            case "in":
                queryWrapper.in(getHumpProperty(), getValues());
                break;
            case "notIn":
                queryWrapper.notIn(getHumpProperty(), getValues());
                break;
            case "between":
                queryWrapper.between(getHumpProperty(), getLowValue(), getHighValue());
                break;
            case "notBetween":
                queryWrapper.notBetween(getHumpProperty(), getLowValue(), getHighValue());
                break;
            case "like":
                queryWrapper.like(getHumpProperty(), getValue());
                break;
            case "notLike":
                queryWrapper.notLike(getHumpProperty(), getValue());
        }
        return queryWrapper;
    }

    public boolean isValid() {
        if (!this.isEnabled()) {
            return false;
        }
        if (!this.isValidOperator(this.getOperator())) {
            return false;
        }
        switch (this.getOperator()) {
            case "eq":
            case "ge":
            case "gt":
            case "le":
            case "lt":
            case "ne":
            case "like":
            case "notLike": {
                if (this.getValue() != null && StringUtils.trimToNull(this.getValue().toString()) != null)
                    break;
                return false;
            }
            case "between": {
                if (this.getHighValue() != null && StringUtils.trimToNull(this.getHighValue().toString()) != null
                        || this.getLowValue() != null
                        && StringUtils.trimToNull(this.getLowValue().toString()) != null)
                    break;
                return false;
            }
            case "notBetween": {
                if (this.getHighValue() == null
                        || StringUtils.trimToNull(this.getHighValue().toString()) == null) {
                    return false;
                }
                if (this.getLowValue() != null && StringUtils.trimToNull(this.getLowValue().toString()) != null)
                    break;
                return false;
            }
            case "in":
            case "notIn": {
                if (this.getValues() != null && this.getValues().size() != 0)
                    break;
                return false;
            }
            case "isNull":
            case "isNotNull": {
                return true;
            }
            default: {
                return false;
            }
        }
        return true;
    }

    private boolean isValidOperator(String operator) {
        if (operator == null) {
            return false;
        }
        Object[] operators = new String[]{isNull, isNotNull, eq, ne, lt, le, gt, ge, in, notIn, between, notBetween,
                like, notLike};
        return ArrayUtils.contains(operators, operator);
    }

    public String getOperatorRelation() {
        return operatorRelation;
    }

    public void setOperatorRelation(String operatorRelation) {
        this.operatorRelation = operatorRelation;
    }
}
