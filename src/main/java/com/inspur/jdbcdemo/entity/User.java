package com.inspur.jdbcdemo.entity;


import java.io.Serializable;
import java.sql.Timestamp;

public class User implements Serializable {
    // serialVersionUID
    private static final long serialVersionUID = 1L;

    private Long id;
    private String username;
    private String password;
    private Boolean loacked =  Boolean.FALSE;
    private Timestamp lastLogin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getLoacked() {
        return loacked;
    }

    public void setLoacked(Boolean loacked) {
        this.loacked = loacked;
    }

    public Timestamp getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }



    @Override
    public String toString() {
        //  如果程序对附加字符串的需求很频繁，不建议使用+来进行字符串的串联。可以考虑使用java.lang.StringBuilder 类
        StringBuilder builder = new StringBuilder();
        builder.append("User [id=").append(id).append(", username=").append(username).append(", password=")
                .append(password).append(", loacked=").append(loacked).append(", lastLogin=").append(lastLogin).append("]");
        return builder.toString();

//        return "User [" +
//                "id=" + id +
//                ", username='" + username + '\'' +
//                ", password=" + password +
//                ", loacked=" + loacked +
//                ", lastLogi=" + lastLogin +
//                '}';
    }
}
