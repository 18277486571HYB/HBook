package www.hyb.dao;

import www.hyb.pojo.user;

public interface UserDao {

    /*首先我们是注册，当我们注册的时候会要保存用户基本信息，而用户这个基本信息就放在了pojo包下的user中*/
    public void saveUser(user u);

    /*其次便是我们登陆*/
    /*首先是用户名确认*/
    public user queryUserName(String username);
    /*其次便是密码确认*/
    public user queryUserPassword(String username,String password);

    public Integer queryId(user u);
}
