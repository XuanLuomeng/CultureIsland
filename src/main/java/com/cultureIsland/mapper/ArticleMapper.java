package com.cultureIsland.mapper;

import com.cultureIsland.pojo.Article;

import java.util.List;

public interface ArticleMapper {
    /**
     * 获取所有文章的分页查询(包括模糊查询)
     *
     * @return
     */
    List<Article> getAllArticle(String str);

    /**
     * 获取用户文章的分页查询(包括模糊查询)
     *
     * @param str
     * @return
     */
    List<Article> getUserArticle(String str, String uid);

    /**
     * 文章发布
     * @param article
     */
    void insertArticle(Article article);

    /**
     * 通过aid删除文章
     * @param aid
     */
    void deleteArticleByAid(int aid);

    /**
     * 文章修改
     * @param article
     */
    void updateArticle(Article article);
}
