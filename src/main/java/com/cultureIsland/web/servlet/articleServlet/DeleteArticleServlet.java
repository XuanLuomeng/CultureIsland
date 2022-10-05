package com.cultureIsland.web.servlet.articleServlet;

import com.cultureIsland.service.ArticleService;
import com.cultureIsland.service.impl.ArticleServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deleteArticle")
public class DeleteArticleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        ArticleService articleService = new ArticleServiceImpl();
        articleService.deleteArticleByAid(Integer.parseInt(req.getParameter("aid")));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        this.doGet(req, resp);
    }
}
