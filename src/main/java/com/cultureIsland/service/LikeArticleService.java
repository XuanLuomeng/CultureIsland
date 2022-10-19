package com.cultureIsland.service;

public interface LikeArticleService {
    /**
     * 用户是否点赞过该文章
     *
     * @param uid
     * @param aid
     * @return
     */
    String isLike(int uid, String aid);

    /**
     * 获取点赞aid列表
     *
     * @param uid
     * @return
     */
    String getLikeArray(int uid);

    /**
     * 修改点赞aid列表
     *
     * @param uid
     * @param likeArray
     */
    void updateLikeArray(int uid, String likeArray);
}
