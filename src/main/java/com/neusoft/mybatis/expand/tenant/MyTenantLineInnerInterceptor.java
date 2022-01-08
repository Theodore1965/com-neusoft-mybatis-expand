package com.neusoft.mybatis.expand.tenant;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.ExceptionUtils;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.ItemsList;
import net.sf.jsqlparser.expression.operators.relational.MultiExpressionList;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.select.Select;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class MyTenantLineInnerInterceptor extends TenantLineInnerInterceptor {

    private TenantLineHandler tenantLineHandler;

    @Autowired
    private TenantProperties tenantProperties;

    public MyTenantLineInnerInterceptor() {

    }

    public MyTenantLineInnerInterceptor(TenantLineHandler tenantLineHandler) {
        this.tenantLineHandler = tenantLineHandler;
    }

    @Override
    protected void processInsert(Insert insert, int index, String sql, Object obj) {
        if (tenantLineHandler.ignoreTable(insert.getTable().getName())) {
            // 过滤退出执行
            return;
        }
        List<Column> columns = insert.getColumns();
        if (CollectionUtils.isEmpty(columns)) {
            // 针对不给列名的insert 不处理
            return;
        }

        String tenantIdColumn = tenantLineHandler.getTenantIdColumn();
        // 针对已给出租户列的insert
        if (columns.stream().map(Column::getColumnName).anyMatch(i -> i.equals(tenantIdColumn))) {
            if (!tenantProperties.isFilterTenantKey()) {
                return;
            } else {
                // 如果语句存在租户字段，则删除，不影响之后逻辑
                int tenantIndex = -1;
                int size = columns.size();
                for (int i = 0; i < size; i++) {
                    if (tenantLineHandler.getTenantIdColumn().equals(columns.get(i).getColumnName())) {
                        tenantIndex = i;
                        break;
                    }
                }
                if (tenantIndex != -1) {
                    insert.getColumns().remove(tenantIndex);

                    ItemsList itemsList = insert.getItemsList();
                    if (itemsList instanceof ExpressionList) {
                        ((ExpressionList) insert.getItemsList()).getExpressions().remove(tenantIndex);
                    }
                }
            }
        }
        columns.add(new Column(tenantLineHandler.getTenantIdColumn()));

        // fixed gitee pulls/141 duplicate update
        List<Expression> duplicateUpdateColumns = insert.getDuplicateUpdateExpressionList();
        if (CollectionUtils.isNotEmpty(duplicateUpdateColumns)) {
            EqualsTo equalsTo = new EqualsTo();
            equalsTo.setLeftExpression(new StringValue(tenantLineHandler.getTenantIdColumn()));
            equalsTo.setRightExpression(tenantLineHandler.getTenantId());
            duplicateUpdateColumns.add(equalsTo);
        }

        Select select = insert.getSelect();
        if (select != null) {
            this.processInsertSelect(select.getSelectBody());
        } else if (insert.getItemsList() != null) {
            // fixed github pull/295
            ItemsList itemsList = insert.getItemsList();
            if (itemsList instanceof MultiExpressionList) {
                ((MultiExpressionList) itemsList).getExprList().forEach(el -> el.getExpressions().add(tenantLineHandler.getTenantId()));
            } else {
                ((ExpressionList) itemsList).getExpressions().add(tenantLineHandler.getTenantId());
            }
        } else {
            throw ExceptionUtils.mpe("Failed to process multiple-table update, please exclude the tableName or statementId");
        }
    }
}
