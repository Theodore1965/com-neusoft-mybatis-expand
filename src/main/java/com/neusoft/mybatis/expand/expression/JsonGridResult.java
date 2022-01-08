package com.neusoft.mybatis.expand.expression;

import java.io.Serializable;
import java.util.List;

public class JsonGridResult<T extends Serializable> implements Serializable
{
    private static final long serialVersionUID = -4465514719987771336L;
    private List<T> rows;
    private long total;

    public JsonGridResult() {
    }

    public JsonGridResult(final List<T> rows, final long total) {
        this.rows = rows;
        this.total = total;
    }

    public List<T> getRows() {
        return this.rows;
    }

    public void setRows(final List<T> rows) {
        this.rows = rows;
    }

    public long getTotal() {
        return this.total;
    }

    public void setTotal(final long total) {
        this.total = total;
    }
}
