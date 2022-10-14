package com.cultureIsland.web.servlet.articleServlet;

import com.cultureIsland.service.ArticleService;
import com.cultureIsland.service.impl.ArticleServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 添加文章的浏览次数
 */
@WebServlet("/addArticleViewCount")
public class AddArticleViewCountServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int aid = Integer.parseInt(req.getParameter("aid"));
        ArticleService articleService = new ArticleServiceImpl();
        articleService.addArticleViewCountByAid(aid);
    }
}
