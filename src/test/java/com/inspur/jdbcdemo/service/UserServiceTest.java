package com.inspur.jdbcdemo.service;

import com.inspur.jdbcdemo.entity.User;
import com.inspur.jdbcdemo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Log4jConfigurer;

import java.io.FileNotFoundException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class UserServiceTest {

    static {
        try {
            Log4jConfigurer.initLogging("classpath:log4j.properties");
        } catch (FileNotFoundException ex) {
            System.err.println("Cannot Initialize log4j");
        }
    }

    @Autowired
    private UserService userService;

    private Logger logger = Logger.getLogger(UserServiceTest.class);

    @Test
    public void testRegister() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("123456");
        User registerUser = userService.register(user);
        logger.info(registerUser);

        User user2 = new User();
        user2.setUsername("guest");
        user2.setPassword("123");
        User registerUser2 = userService.register(user2);
        logger.info(registerUser2);
    }

    @Test
    public void testLogin() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("123456");
        User registerUser = userService.login(user);
        logger.info(registerUser);
    }

    @Test
    public void testLogin2() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("123");
        User registerUser = userService.login(user);
        logger.info(registerUser);
    }

    @Test
    public void testLogin3() {
        User user = new User();
        user.setUsername("adm");
        user.setPassword("123456");
        User registerUser = userService.login(user);
        logger.info(registerUser);
    }

    @Test
    public void testChangePassword() {
        userService.changePassword(1L, "123");
    }
}
