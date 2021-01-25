package com.inspur.jdbcdemo.dao;

import com.inspur.jdbcdemo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * 用户dao实现类
 */
public class UserDaoImpl implements UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User save(final User user) {
        // sql语句
        final String sql = "insert into sys_user(username,password,locked,last_login) values(?,?,?,?)";

        // 使用jdbcTemplate执行sql语句，避免sql注入
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, new String[] { "id" });
                int index = 1;
                ps.setString(index++, user.getUsername());
                ps.setString(index++, user.getPassword());
                ps.setBoolean(index++, user.getLoacked());
                ps.setTimestamp(index++, user.getLastLogin());
                return ps;

            }
        }, keyHolder);
        //回填主键
        user.setId(keyHolder.getKey().longValue());
        return user;
    }

    @Override
    public User update(User user) {
        String sql = "update sys_user set username=?,password=?,locked=?,last_login=? where id=?";
        jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getLoacked(), user.getLastLogin(), user.getId());
        return user;
    }

    @Override
    public void delete(Long id) {
        String sql = "delete from sys_user where id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public User findOne(Long id) {
        String sql = "select * from sys_user where id = ?";
        User user = (User) jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper(User.class), id);
        return user;
    }

    @Override
    public User findByUsername(String username) {
        String sql = "select * from sys_user where username=?";
        List<User> users = jdbcTemplate.query(sql, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setLoacked(rs.getBoolean("locked"));
                user.setLastLogin(rs.getTimestamp("last_login"));
                return user;
            }
        }, username);
        if (users.size() == 0)
            return null;
        return users.get(0);
    }

    @Override
    public List<User> findAll() {
        String sql = "select * from sys_user";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper(User.class));
    }


}
