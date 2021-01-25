package com.inspur.jdbcdemo.dao;

import java.util.List;

import com.inspur.jdbcdemo.entity.User;

/**
 * 用户dao
 */
public interface UserDao {
    User save(User user);

    User update(User user);

    void delete(Long id);

    User findOne(Long id);

    User findByUsername(String username);

    List<User> findAll();

}
