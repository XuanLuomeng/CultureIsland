package com.cultureIsland.web.servlet.articleServlet;

import com.cultureIsland.pojo.Article;
import com.cultureIsland.service.ArticleService;
import com.cultureIsland.service.UserService;
import com.cultureIsland.service.impl.ArticleServiceImpl;
import com.cultureIsland.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 发布文章
 */
@WebServlet("/InsertArticle")
public class InsertArticleServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * 获取参数，并获取当前时间
         */
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        Date pushdate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd ");
        /**
         * 获取当前用户uid信息,并用该uid去上传文章
         */
        HttpSession httpSession = req.getSession();
        Object user = httpSession.getAttribute("userId");
        String userId = (String) user;
        UserService userService = new UserServiceImpl();
        int uid = userService.getUidByUserId(userId);

        /**
         * 设置文章相关信息
         */
        Article articlePush = new Article();
        articlePush.setUid(uid);
        articlePush.setTitle(title);
        articlePush.setContent(content);
        articlePush.setDate(simpleDateFormat.format(pushdate));

        /**
         * 保存至数据库
         */
        ArticleService articleService = new ArticleServiceImpl();
        articleService.insertArticle(articlePush);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
