package com.inspur.jdbcdemo.service;
import java.util.List;
import com.inspur.jdbcdemo.entity.User;

/**
 * 用户服务
 */
public interface UserService {
    // 注册
    User register(User user);

    // 登录
    User login(User user);

    // 修改密码
    void changePassword(Long userId, String newPassword);

    // 查询
    List<User> findAll();

}
