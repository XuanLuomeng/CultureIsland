package com.cultureIsland.service;

import com.cultureIsland.pojo.User;

public interface UserService {
    /**
     * check whether the user exists
     *
     * @param userId
     * @return
     */
    boolean isExistUser(String userId);

    /**
     * Insert user(include password encryption)
     *
     * @param user
     */
    void insertUser(User user);

    /**
     * check userid and password
     *
     * @param userId
     * @param password
     */
    boolean checkPassword(String userId, String password);

    /**
     * 通过用户账号获取用户id
     *
     * @param userId
     * @return
     */
    int getUidByUserId(String userId);
}
