package com.cultureIsland.service;

import com.cultureIsland.pojo.Comment;
import com.cultureIsland.pojo.Page;

public interface CommentService {
    /**
     * 通过文章id分页获取评论
     *
     * @param aid
     * @return
     */
    Page getCommentPageInfo(int pageNum, int aid);

    /**
     * 通过用户id和文章id发布评论
     *
     * @param comment
     */
    void insertCommentByUidAndAid(Comment comment);

    /**
     * 通过评论id删除评论
     *
     * @param cid
     */
    void deleteCommentByCid(int cid);
}
