package com.theodore.utils;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.expr.SQLBinaryOpExpr;
import com.alibaba.druid.sql.ast.expr.SQLCharExpr;
import com.alibaba.druid.sql.ast.expr.SQLIdentifierExpr;
import com.alibaba.druid.sql.ast.expr.SQLIntegerExpr;
import com.alibaba.druid.sql.ast.statement.SQLInsertStatement;
import com.alibaba.druid.sql.ast.statement.SQLUpdateSetItem;
import com.alibaba.druid.sql.dialect.oracle.ast.stmt.OracleInsertStatement;
import com.alibaba.druid.sql.dialect.oracle.ast.stmt.OracleUpdateStatement;
import com.alibaba.druid.sql.dialect.oracle.parser.OracleStatementParser;
import com.alibaba.druid.sql.dialect.oracle.visitor.OracleSchemaStatVisitor;
import com.alibaba.druid.sql.parser.SQLStatementParser;
import com.alibaba.druid.stat.TableStat;
import com.alibaba.druid.util.JdbcConstants;
import com.alibaba.druid.util.JdbcUtils;
import org.springframework.core.env.Environment;

import java.util.*;

public abstract class SqlParser {

    private String dbType;

    public String getDbType() {
        return this.dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    /**
     * 构造方法
     */
    public SqlParser() {
        this.dbType = getDbTypeStr();
    }

    public abstract void getSqlProperty();

    /**
     * 解析sql语句
     *
     * @param statement
     * @return
     */
    public void parse(String statement) {
        // 使用druid解析语句
        // 第一个参数为SQL语句
        // 第二个参数为解析的数据库类型
        List<SQLStatement> statementList = SQLUtils.parseStatements(statement, getDbType());
        // 单语句解析，只有一条数据
        if (!statementList.isEmpty()) {
            SQLStatement sqlStatement = statementList.get(0);
            // 插入语句解析
            if (sqlStatement instanceof OracleInsertStatement) {
                // 转换
                OracleInsertStatement insertStatement = (OracleInsertStatement) sqlStatement;
                // 获取列名
                List<SQLExpr> columns = insertStatement.getColumns();
                List<String> columnsName = new ArrayList<>(columns.size());
                for (SQLExpr column : columns) {
                    columnsName.add(((SQLIdentifierExpr) column).getName());
                }
                System.out.println(columnsName);
                // 获取值
                List<SQLInsertStatement.ValuesClause> valuesList = insertStatement.getValuesList();
                List<List<Object>> dataList = new ArrayList<>();
                for (SQLInsertStatement.ValuesClause valuesClause : valuesList) {
                    List<SQLExpr> values = valuesClause.getValues();
                    List<Object> data = new ArrayList<>(columnsName.size());
                    for (SQLExpr value : values) {
                        data.add(getValue(value));
                    }
                    dataList.add(data);
                }
                System.out.println(dataList);
                // 获取表名
                System.out.println(insertStatement.getTableName().getSimpleName());

            } else if (sqlStatement instanceof OracleUpdateStatement) {
                // 更新语句解析
                OracleUpdateStatement updateStatement = (OracleUpdateStatement) sqlStatement;
                // 获取更新的值和内容
                List<SQLUpdateSetItem> items = updateStatement.getItems();
                Map<String, Object> updateMap = new HashMap<>(items.size());
                for (SQLUpdateSetItem item : items) {
                    updateMap.put(((SQLIdentifierExpr) item.getColumn()).getName(), getValue(item.getValue()));
                }
                System.out.println(updateMap);
                // 获取条件，条件比较复杂，需根据实际情况自行提取
                SQLBinaryOpExpr where = (SQLBinaryOpExpr) updateStatement.getWhere();
                System.out.println(where);
                // 获取表名
                System.out.println(updateStatement.getTableName().getSimpleName());
            }
        }
    }

    /**
     * 获取数据库驱动
     * @return
     */
    public String getDbDriver() {
        return environment().getProperty("spring.datasource.driverClassName");
    }

    /**
     * 根据配置文件中的数据库驱动类型，获取数据库类型
     *
     * @return
     */
    public String getDbTypeStr() {
        String driver = getDbDriver();

        if (JdbcUtils.isOracleDbType(driver)) {
            return JdbcConstants.ORACLE;
        }

        if (JdbcUtils.H2.equals(driver)) {
            return JdbcConstants.H2;
        }

        if (JdbcUtils.isMysqlDbType(driver)) {
            return JdbcConstants.MYSQL;
        }

        if (JdbcUtils.isPgsqlDbType(driver)) {
            // return JdbcConstants.PSQ
        }

        if (JdbcUtils.isSqlserverDbType(driver)) {
            return JdbcConstants.SQL_SERVER;
        }

        if (JdbcUtils.DB2.equals(driver)) {
            return JdbcConstants.DB2;
        }

        if (JdbcUtils.ODPS.equals(driver)) {
            return JdbcConstants.ODPS;
        }

        if (JdbcUtils.PHOENIX.equals(driver)) {
            return JdbcConstants.PHOENIX;
        }

        if (JdbcUtils.HIVE.equals(driver)) {
            return JdbcConstants.HIVE;
        }

        if (JdbcUtils.ELASTIC_SEARCH.equals(driver)) {
            return JdbcConstants.ELASTIC_SEARCH;
        }
        return null;
    }

    private Object getValue(SQLExpr value) {
        // TODO 判断更多的种类
        if (value instanceof SQLIntegerExpr) {
            // 值是数字
            return ((SQLIntegerExpr) value).getNumber();
        } else if (value instanceof SQLCharExpr) {
            // 值是字符串
            return ((SQLCharExpr) value).getText();
        }
        return null;
    }

    private Environment environment() {
        return BeanContainer.getBean(Environment.class);
    }

    private static void getAllTableNameBySQL(String sql) {
        SQLStatementParser parser = new OracleStatementParser(sql);
        // 使用Parser解析生成AST，这里SQLStatement就是AST
        SQLStatement sqlStatement = parser.parseStatement();
        OracleSchemaStatVisitor visitor = new OracleSchemaStatVisitor();
        sqlStatement.accept(visitor);

        // 查询字段
        Collection<TableStat.Column> columns = visitor.getColumns();
        // 条件
        List<TableStat.Condition> conditionList = visitor.getConditions();
        for (TableStat.Condition condition : conditionList) {
            System.out.println(condition.getColumn().getName());
            System.out.println(condition.getOperator());
            System.out.println(condition.getValues());
        }
        // 分组
        Set<TableStat.Column> groups =  visitor.getGroupByColumns();
        for (TableStat.Column group : groups) {
            System.out.println(group.getName());
        }
        // 排序
        List<TableStat.Column> columnss = visitor.getOrderByColumns();
        for (TableStat.Column group : columnss) {
            System.out.println(group.getName());
        }
//        System.out.println(columns);
//        System.out.println(conditionList);
//        System.out.println(groups);
//        System.out.println(columnss);
//        Map<TableStat.Name, TableStat> tables = visitor.getTables();
//        List<String> allTableName = new ArrayList<>();
//        for (TableStat.Name t : tables.keySet()) {
//            allTableName.add(t.getName());
//        }
//        return allTableName;
    }
    public static void main(String[] args) throws Exception {
        String sql = "SELECT '', --父级医疗机构编码\n" +
                "       '', --本级医疗机构编码\n" +
                "       id, --序号\n" +
                "       week, --星期\n" +
                "       schema_type, --模板类型，0科室模板/1医生模板\n" +
                "       dept_code, --科室号\n" +
                "       dept_name, --科室名称\n" +
                "       doct_code, --医生代码\n" +
                "       doct_name, --医生名称\n" +
                "       noon_code, --午别\n" +
                "       reg_lmt, --挂号限额\n" +
                "       valid_flag, --1有效/0无效\n" +
                "       remark, --备注\n" +
                "       oper_code, --操作员代码\n" +
                "       oper_date, --最近变动日期\n" +
                "       doct_type, --医生类别\n" +
                "       begin_time, --开始时间\n" +
                "       end_time, --结束时间\n" +
                "       tel_lmt, --电话预约\n" +
                "       spe_lmt, --特诊预约\n" +
                "       app_flag, --是否加号 1是/0否\n" +
                "       reason_no,\n" +
                "       reason_name,\n" +
                "       reglevl_code,\n" +
                "       reglevl_name,\n" +
                "       app_lmt,\n" +
                "       onefour_yls, --114预留数\n" +
                "       onefour_reg, --114 预约数\n" +
                "     prebegin_time,--预约停诊开始时间\n" +
                "     preend_time --预约停诊结束时间\n" +
                "  FROM fin_opr_schematemplet --排班模板表 \n" +
                "-- WHERE week = '1'\n" +
                " -- AND (dept_code ='5099' or 'ALL' ='5099') --当传入参数为'ALL'时，检索所有科室项目，否则检索具体科室项目\n" +
                "--  AND schema_type='1' \n" +
                "WHERE \n" +
                " REGLEVL_CODE = '20'\n" +
                "ORDER BY dept_code,doct_code,noon_code,app_flag,begin_time; ";

        String sql1 = "";
        getAllTableNameBySQL(sql);
//        SqlServerParser sqlServerParser = new SqlServerParser();
//        sqlServerParser.parse("update test set status='P' where id=20");
//        sqlServerParser.parse("insert into test (id,status,name,ce,acc) values (29,'P','lll','sxsx','Arferwg')");
    }
}
