package com.cultureIsland.web.servlet.articleServlet;

import com.cultureIsland.service.ArticleService;
import com.cultureIsland.service.LikeArticleService;
import com.cultureIsland.service.impl.ArticleServiceImpl;
import com.cultureIsland.service.impl.LikeArticleServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 通过aid删除文章,以及删除点赞列表中有该aid的数据
 */
@WebServlet("/deleteArticle")
public class DeleteArticleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        if (req.getParameter("aid") != null) {
            if (req.getParameter("aid") != "") {
                int aid = Integer.parseInt(req.getParameter("aid"));
                ArticleService articleService = new ArticleServiceImpl();
                articleService.deleteArticleByAid(aid);
                LikeArticleService likeArticleService = new LikeArticleServiceImpl();
                likeArticleService.updateLikeArrays(String.valueOf(aid));
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        this.doGet(req, resp);
    }
}
