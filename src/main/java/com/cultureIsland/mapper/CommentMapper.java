package com.cultureIsland.mapper;

import com.cultureIsland.pojo.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentMapper {
    /**
     * 通过文章id获取评论
     *
     * @return
     */
    List<Comment> getCommentByAid(@Param("aid") int aid);

    /**
     * 通过文章id和用户id添加评论
     *
     * @param comment
     */
    void insertCommentByUidAndAid(Comment comment);

    /**
     * 通过cid删除评论
     *
     * @param cid
     */
    void deleteCommentByCid(@Param("cid") int cid);
}
