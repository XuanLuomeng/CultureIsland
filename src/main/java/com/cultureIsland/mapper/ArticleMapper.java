package com.cultureIsland.mapper;

import com.cultureIsland.pojo.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 文章mapper
 */
public interface ArticleMapper {
    /**
     * 获取所有文章(包括模糊查询)
     *
     * @return
     */
    List<Article> getAllArticle(@Param("str") String str);

    /**
     * 获取用户文章(包括模糊查询)
     *
     * @param str
     * @return
     */
    List<Article> getUserArticle(@Param("str") String str, @Param("uid") int uid);

    /**
     * 通过aid获取文章内容
     *
     * @param aid
     * @return
     */
    Article getUserArticleByAid(@Param("aid") int aid);

    /**
     * 文章发布
     *
     * @param article
     */
    void insertArticle(Article article);

    /**
     * 通过aid删除文章
     *
     * @param aid
     */
    void deleteArticleByAid(@Param("aid") int aid);

    /**
     * 文章修改
     *
     * @param article
     */
    void updateArticle(Article article);

    /**
     * 通过文章aid增加浏览次数
     */
    void addViewCountByAid(@Param("aid") int aid);
}
