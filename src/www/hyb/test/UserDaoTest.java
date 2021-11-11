package www.hyb.test;

import org.junit.Test;
import www.hyb.dao.impl.UserDaoImpl;
import www.hyb.pojo.user;

import static org.junit.Assert.*;

public class UserDaoTest {

    @Test
    public void saveUser() {
        UserDaoImpl userDao = new UserDaoImpl();
        user user = new user(null,"hfy","hfy","2751265792@qq.com");
        userDao.saveUser(user);
//        数据表插入一个数据
    }

    @Test
    public void queryUserName() {
        UserDaoImpl userDao = new UserDaoImpl();
        System.out.println(userDao.queryUserName("hyb"));
//        user{id=1, username='hyb', password='hyb', email='468949484@qq.com'}
    }

    @Test
    public void queryUserPassword() {
        UserDaoImpl userDao = new UserDaoImpl();
        System.out.println(userDao.queryUserPassword("hyb", "hyb"));
    }
}