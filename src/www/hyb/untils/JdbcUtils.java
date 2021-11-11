package www.hyb.untils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {
    private static DataSource source=null;
    private static final ThreadLocal<Connection> connection=new ThreadLocal<>();
    static {
        try {
            Properties ps = new Properties();
            FileInputStream fs=new FileInputStream("E://javaStudy//HBook//src//Druid.properties");
            ps.load(fs);
            source=  DruidDataSourceFactory.createDataSource(ps);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


//    获取数据库连接池的连接
    public static Connection connect(){
        Connection connect= JdbcUtils.connection.get();
        if (connect==null){
            try {
                connect=source.getConnection();
                connection.set(connect);
//                设置手动管理事务
                connect.setAutoCommit(false);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return connect;
    }
////    关闭数据库连接池的连接
//    public static Connection close(Connection connection){
//        if (connection!=null){
//            try {
//                connection.close();
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//        }
//        return null;
//    }

    public static void commitClose(){
        Connection connect= JdbcUtils.connection.get();
        if (connect!=null){
            try {
                connect.commit();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                try {
                    connect.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        connection.remove();
    }

    public static void rollbackClose(){
        Connection connect= JdbcUtils.connection.get();
        if (connect!=null){
            try {
                connect.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                try {
                    connect.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        connection.remove();
    }

}
