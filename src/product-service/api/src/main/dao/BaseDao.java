package com.idcf.boathouse.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.idcf.boathouse.JdbcUtils;
import com.idcf.boathouse.models.FoodCategory;

public class BaseDao {

    public static List<Map<String, Object>> exec(String sql){
        JdbcUtils jdbcUtils = new JdbcUtils();
        jdbcUtils.getConnection();
        try {
            List<Map<String, Object>> list = jdbcUtils.findModeResult(sql, null);
            System.out.println(list);
            jdbcUtils.releaseConn();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T get(String sql, List<Object> params, Class<T> cls){
        JdbcUtils jdbcUtils = new JdbcUtils();
        jdbcUtils.getConnection();
        T t;
        try {
            t = jdbcUtils.findSimpleRefResult(sql, params, cls);
            System.out.print(cls);
            jdbcUtils.releaseConn();
            return t;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void exec(String sql, List<Object> params){
        JdbcUtils jdbcUtils = new JdbcUtils();
        jdbcUtils.getConnection();
        try {
            boolean flag = jdbcUtils.updateByPreparedStatement(sql, params);
            System.out.println(flag);
            jdbcUtils.releaseConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
