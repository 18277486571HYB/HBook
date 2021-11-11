package www.hyb.dao.impl;

import www.hyb.dao.UserDao;
import www.hyb.pojo.user;

public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public void saveUser(user u) {
        /*保存数据相当于插入一条数据*/
        String sql="insert into user(username,password,email) values(?,?,?)";
        update(sql, u.getUsername(), u.getPassword(), u.getEmail());
    }

    @Override
    public user queryUserName(String username) {
        /*我们要验证的是是否存在该用户，所以我们得选择器所有的列*/
        String sql="select id,username,password,email from user where username=?";
        /*sql语句的username是属性名，而queryOne里的username是user类中的实例化对象的username，是一个具体值*/
        return queryForOne(user.class,sql,username);
    }

    @Override
    public user queryUserPassword(String username, String password) {
        /*返回一个javabean，所以所有属性都有*/
        String sql="select id,username,password,email from user where username=? and password=?";
        return queryForOne(user.class,sql,username,password);
    }

    @Override
    public Integer queryId(user u) {
        String sql="select id from user where username=?";
        return (Integer) queryOneValue(sql,u.getUsername());
    }
}
