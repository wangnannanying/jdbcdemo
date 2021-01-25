package com.inspur.jdbcdemo.service;

import com.inspur.jdbcdemo.dao.UserDao;
import com.inspur.jdbcdemo.entity.User;
import com.inspur.jdbcdemo.exception.MyAppException;
import com.inspur.jdbcdemo.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * 用户服务实现类
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User register(User user) {
        user.setPassword(encryptPassword(user.getPassword()));
        return userDao.save(user);
    }

    // 对密码进行加密
    private String encryptPassword(String password) {
        return MD5Util.MD5(password);
    }

    @Override
    public User login(User userForm) {
        User dbUser = userDao.findByUsername(userForm.getUsername());
        if(dbUser == null) {
            throw new MyAppException("找不到用户名");
        }
        // 更新最后登录时间
        dbUser.setLastLogin(new Timestamp(new Date().getTime()));
        userDao.update(dbUser);
        return dbUser;
    }

    @Override
    public void changePassword(Long userId, String newPassword) {
        User user = userDao.findOne(userId);
        user.setPassword(encryptPassword(newPassword));
        userDao.update(user);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

}
