package com.cultureIsland.mapper;

import com.cultureIsland.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    /**
     * 通过用户账号查询用户
     * @param userId
     * @return
     */
    User selectUserByUserId(@Param("userId") String userId);

    /**
     * 添加用户
     */
    void insertUser(@Param("user") User user);
}
