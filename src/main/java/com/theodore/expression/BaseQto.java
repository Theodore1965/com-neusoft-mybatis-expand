package com.theodore.expression;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.theodore.global.BusinessException;
import io.swagger.annotations.ApiModelProperty;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;


public class BaseQto {

    @ApiModelProperty("排序字段")
    private List<String> sortby;
    @ApiModelProperty("排序方式 ASC/DESC")
    private String sortorder;
    @ApiModelProperty("页数")
    private Integer pagenum;
    @ApiModelProperty("每页条数")
    private Integer pagesize;

    public BaseQto() {
        // 字段属性初始化
//        initField();
    }

    public String getSortorder() {
        return sortorder;
    }

    public void setSortorder(String sortorder) {
        this.sortorder = sortorder;
    }

    public Integer getPagenum() {
        return pagenum;
    }

    public void setPagenum(Integer pagenum) {
        this.pagenum = pagenum;
    }

    public Integer getPagesize() {
        return pagesize;
    }

    public void setPagesize(Integer pagesize) {
        this.pagesize = pagesize;
    }

    public List<String> getSortby() {
        return sortby;
    }

    public void setSortby(List<String> sortby) {
        this.sortby = sortby;
    }

    /**
     * 字段属性初始化
     */
    private void initField() {
        try {
            Field[] fields = this.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (Modifier.isFinal(field.getModifiers()))
                    continue;;
                field.setAccessible(true);
                Object obj = field.get(this);
                if (obj == null) {
                    field.set(this, new SimpleExpression(field.getName(),SimpleExpression.eq));
                }
            }
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    /**
     * 获取查询条件
     * @return
     */
    public QueryWrapper getWrapper() {
        return SimpleExpressionWrapper.getWrapper(this);
    }

    /**
     * 分页
     * @return
     */
    public Page getPage() {
        if (pagenum == null || pagesize == null)
            return new Page();
        return new Page(pagenum, pagesize);
    }
}
