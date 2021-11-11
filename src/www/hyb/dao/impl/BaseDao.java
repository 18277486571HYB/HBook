package www.hyb.dao.impl;


import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import www.hyb.untils.JdbcUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDao {
    private final QueryRunner queryRunner=new QueryRunner();
//    更新插入删除
    public int update(String sql,Object ... arg){
        Connection connect = JdbcUtils.connect();
        try {
             return queryRunner.update(connect,sql,arg);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }
    }

//    查询一个javabean
    public <T> T queryForOne(Class<T> type,String sql,Object ...arg){
        Connection connect = JdbcUtils.connect();
        BeanHandler<T> bh= new BeanHandler<T>(type);
        try {
            return queryRunner.query(connect,sql,bh,arg);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }
    }

//    查询某个值
    public Object queryOneValue(String sql,Object ...arg){
        Connection connect = JdbcUtils.connect();
        ScalarHandler<Object> objectScalarHandler = new ScalarHandler<>();
        try {
            return queryRunner.query(connect,sql,objectScalarHandler,arg);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }
    }

//    查询多个
    public <T> List<T> queryList(Class<T> type,String sql,Object ...arg){
        Connection connect = JdbcUtils.connect();
        BeanListHandler<T> bh= new BeanListHandler<T>(type);
        try {
            return queryRunner.query(connect,sql,bh,arg);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }
    }
}
