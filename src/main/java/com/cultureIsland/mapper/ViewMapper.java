package com.cultureIsland.mapper;

import org.apache.ibatis.annotations.Param;

public interface ViewMapper {
    /**
     * 通过vid来获取视图的url
     *
     * @param vid
     * @return
     */
    String getUrlByVid(@Param("vid") int vid);

    /**
     * 添加url
     *
     * @param url
     */
    void insertUrl(@Param("url") String url);
}
