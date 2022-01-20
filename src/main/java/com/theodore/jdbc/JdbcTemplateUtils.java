package com.theodore.jdbc;

import com.theodore.expression.JsonGridResult;
import com.theodore.mybatis.MyPaginationInnerInterceptor;
import com.theodore.utils.BeanContainer;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;


public class JdbcTemplateUtils {

    private static final String WRAPPING_STATMENT = "SELECT * FROM ( SELECT TMP.*, ROWNUM ROW_ID FROM ({0}) " +
            "TMP WHERE ROWNUM <= ?) WHERE ROW_ID > ?";

    public <T> List<T> queryList(String sql, Class<T> clz, Object... objects) {
        RowMapper<T> rowMapper = new BeanPropertyRowMapper<T>(clz);
        return jdbcTemplate().query(sql, rowMapper, objects);
    }

    public <T extends Object> T query(String sql, Class<T> clz, Object... objects) {
        RowMapper<T> rowMapper = new BeanPropertyRowMapper<T>(clz);
        return jdbcTemplate().queryForObject(sql, rowMapper, objects);
    }

    public <T> JsonGridResult queryPage(String sql, Class<T> clz, Object... objects) {
//        mybatis plus 提供Oracle分页语句封装
//        OracleDialect oracleDialect = new OracleDialect();
//        DialectModel dialectModel = oracleDialect.buildPaginationSql(sql, 0, 5);
//        String newSql = dialectModel.getDialectSql();
        List<T> rows = new ArrayList<>();
        String rowsSql = MessageFormat.format(WRAPPING_STATMENT, sql);
        RowMapper<T> rowMapper = new BeanPropertyRowMapper<T>(clz);
        if(objects != null) {
            rows = jdbcTemplate().query(rowsSql, rowMapper, objects, 0, 5);
        } else {
            rows = jdbcTemplate().query(rowsSql, rowMapper, 0, 5);
        }

        // 总总数
        MyPaginationInnerInterceptor myPaginationInnerInterceptor = new MyPaginationInnerInterceptor();
        String countSql = myPaginationInnerInterceptor.autoCountSql(sql);
        long total = jdbcTemplate().queryForObject(countSql, Long.class);
        return new JsonGridResult(rows, total);
    }

    private JdbcTemplate jdbcTemplate() {
        return BeanContainer.getBean(JdbcTemplate.class);
    }

}
