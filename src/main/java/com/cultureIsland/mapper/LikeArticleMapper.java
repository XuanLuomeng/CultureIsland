package com.cultureIsland.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * 点赞mapper
 */
public interface LikeArticleMapper {
    /**
     * 通过uid获取点赞的文章aid列表（包含模糊查询功能，用于检测是否点赞过某篇文章）
     *
     * @param uid
     * @return
     */
    String getLikeArrayOrIsLikeByUidAndAid(@Param("uid") int uid, @Param("aid") String aid);

    /**
     * 通过修改点赞aid列表来增加点赞的文章
     *
     * @param uid
     * @param likeArray
     */
    void insertLikeArticleByUpdateLikeArray(@Param("uid") int uid, @Param("likeArray") String likeArray);
}
