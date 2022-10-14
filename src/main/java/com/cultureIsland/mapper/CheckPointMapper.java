package com.cultureIsland.mapper;

import com.cultureIsland.pojo.CheckPoint;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 闯关mapper
 */
public interface CheckPointMapper {
    /**
     * 根据用户uid获取闯关的数据
     *
     * @param uid
     * @return
     */
    String getCpNumByUid(@Param("cpUid") int uid);

    /**
     *
     * @param uid
     * @param cpNum
     */
    void updateCpNumByUid(int uid, String cpNum);
}
