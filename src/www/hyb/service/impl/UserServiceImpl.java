package www.hyb.service.impl;

import www.hyb.dao.UserDao;
import www.hyb.dao.impl.UserDaoImpl;
import www.hyb.pojo.user;
import www.hyb.service.UserService;

public class UserServiceImpl implements UserService {

    /*UserService实现类进行登录注册还有用户名合法验证的实现*/

//    要操作数据库，而操作数据库的接口和类在UserDao里
    private final UserDao userDao=new UserDaoImpl();

    @Override
    public void RegisterUser(user u) {
        userDao.saveUser(u);
    }

    @Override
    public user Login(user u) {
        return userDao.queryUserPassword(u.getUsername(), u.getPassword());
    }

    @Override
    public boolean isLegal(String username) {
        return userDao.queryUserName(username) == null;
    }

    @Override
    public Integer getUserId(user u) {
        return userDao.queryId(u);
    }
}
