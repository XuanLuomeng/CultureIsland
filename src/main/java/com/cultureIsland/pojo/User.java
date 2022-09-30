package com.cultureIsland.pojo;

import lombok.Data;

@Data
public class User {
    private String userId;
    private String password;
    private String salt;
    private String userName;
    private String sex;
    private String birthday;
    private String telephone;
    private String email;

    public User(){}

    public User(String userId, String password, String salt, String userName, String sex, String telephone, String birthday, String email) {
        this.userId = userId;
        this.password = password;
        this.salt = salt;
        this.userName = userName;
        this.sex = sex;
        this.telephone = telephone;
        this.birthday = birthday;
        this.email = email;
    }

}
