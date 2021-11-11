package www.hyb.test;

import org.junit.Test;
import www.hyb.pojo.user;
import www.hyb.service.impl.UserServiceImpl;

import static org.junit.Assert.*;

public class UserServiceTest {
    UserServiceImpl userService = new UserServiceImpl();
    user user = new user(null,"hlf","hlf","468949484@qq.com");
    @Test
    public void registerUser() {

        userService.RegisterUser(user);
    }

    @Test
    public void login() {
        System.out.println(userService.Login(user));
    }

    @Test
    public void isLegal() {
        System.out.println(userService.isLegal("hlf"));
    }
}