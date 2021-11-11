package www.hyb.service;

import www.hyb.pojo.user;

public interface UserService {
    /*前面我们规范了UserDao，该接口你会发现就是一个保存用户数据
    * 一个是验证用户名
    * 一个是根据用户名验证密码
    * 但是，你会发现这只是一些零散的功能，而UserService接口出现，将他们整合起来*/

//    注册接口
    public void RegisterUser(user u);

//    登录接口
    public user Login(user u);

//    注册的时候，验证用户名是否合法
    public boolean isLegal(String username);

//    获取id
    public Integer getUserId(user u);
}
