package com.cultureIsland.web.servlet.articleServlet;

import com.cultureIsland.pojo.Article;
import com.cultureIsland.service.ArticleService;
import com.cultureIsland.service.impl.ArticleServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 修改文章内容
 */
@WebServlet("/UpdateArticle")
public class UpdateArticleServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        /**
         * 获取修改内容
         */
        Article article = new Article();
        article.setAid(Integer.parseInt(req.getParameter("aid")));
        article.setTitle(req.getParameter("title"));
        article.setContent(req.getParameter("content"));

        /**
         * 执行修改操作
         */
        ArticleService articleService = new ArticleServiceImpl();
        articleService.updateArticle(article);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        this.doPost(req, resp);
    }
}
