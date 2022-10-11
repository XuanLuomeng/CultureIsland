package com.cultureIsland.mapper;

import com.cultureIsland.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    /**
     * 通过用户账号查询用户
     *
     * @param userId
     * @return
     */
    User selectUserByUserId(@Param("userId") String userId);

    /**
     * 添加用户
     */
    void insertUser(User user);

    /**
     * 通过用户账号获取用户名
     * @param userId
     * @return
     */
    String selectUserNameByUserId(@Param("userId") String userId);

    /**
     * 通过用户账号获取uid
     * @param userId
     * @return
     */
    int selectUidByUserId(@Param("userId")String userId);
}
