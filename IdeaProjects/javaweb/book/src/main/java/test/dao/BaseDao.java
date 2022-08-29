package test.dao;


import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import test.utils.jdbcutils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDao {
    private QueryRunner queryRunner = new QueryRunner();
    public int update(String sql, Object...args){
        Connection connection = jdbcutils.getConnection();
        try {
            return queryRunner.update(connection,sql,args);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    public <T> int queryForone(Class<T> type, String sql, Object...args){
        Connection connection = jdbcutils.getConnection();
        try {
            return queryRunner.update(connection,sql, new BeanHandler<T>(type),args);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

}
