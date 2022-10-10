package com.cultureIsland.mapper;

import com.cultureIsland.pojo.CheckPoint;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CheckPointMapper {
    List<CheckPoint> getCpNumByUid(@Param("cpUid") int uid);
}
