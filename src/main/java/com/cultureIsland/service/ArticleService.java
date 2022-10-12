package com.cultureIsland.service;

import com.cultureIsland.pojo.Article;
import com.cultureIsland.pojo.Page;

public interface ArticleService {
    /**
     * 通过文章页码获取分页信息(包括搜索功能)
     *
     * @param pageNum
     * @return
     */
    Page getArticlePageInfo(int pageNum, String str);

    /**
     * 通过文章页码获取用户分页信息(包括搜索功能)
     *
     * @param pageNum
     * @param str
     * @param uid
     * @return
     */
    Page getUserArticlePageInfo(int pageNum, String str, int uid);

    /**
     * 发布文章
     *
     * @param article
     */
    void insertArticle(Article article);

    /**
     * 通过文章aid删除文章
     *
     * @param aid
     */
    void deleteArticleByAid(int aid);

    /**
     * 修改文章标题，内容
     *
     * @param article
     */
    void updateArticle(Article article);

    /**
     * 通过文章aid增加文章的浏览次数
     *
     * @param aid
     */
    void addArticleViewCountByAid(int aid);
}
