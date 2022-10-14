package com.cultureIsland.web.servlet.articleServlet;

import com.cultureIsland.pojo.Article;
import com.cultureIsland.pojo.Comment;
import com.cultureIsland.pojo.Page;
import com.cultureIsland.service.ArticleService;
import com.cultureIsland.service.CommentService;
import com.cultureIsland.service.UserService;
import com.cultureIsland.service.impl.ArticleServiceImpl;
import com.cultureIsland.service.impl.CommentServiceImpl;
import com.cultureIsland.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;

/**
 * 分页获取文章
 */
@WebServlet("/Article")
public class GetArticleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * 获取请求参数（封装文章）
         */
        String currentPageStr = req.getParameter("currentPage");
        String title = req.getParameter("title");
        HttpSession httpSession = req.getSession();
        Object userId = httpSession.getAttribute("userId");
        int uid = 0;
        if (userId != null) {
            UserService userService = new UserServiceImpl();
            uid = userService.getUidByUserId((String) userId);
        }

        /**
         * 处理参数(防止空指针异常)
         */
        int currentPage = 0;
        if (currentPageStr != null && currentPageStr.length() > 0) {
            currentPage = Integer.parseInt(currentPageStr);
        } else {
            currentPage = 1;
        }
        if (title == null || title.length() < 0) {
            title = "";
        }

        /**
         * 查询文章Page对象
         */
        Page<Article> page = null;
        ArticleService articleService = new ArticleServiceImpl();
        try {
            page = articleService.getArticlePageInfo(currentPage, title, uid);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        /**
         * 获取请求参数（封装评论）
         */
        String currentPageStr1 = req.getParameter("currentCommentPage");

        /**
         * 处理参数
         */
        int currentPage1 = 0;
        if (currentPageStr1 != null && currentPageStr1.length() > 0) {
            currentPage1 = Integer.parseInt(currentPageStr1);
        } else {
            currentPage1 = 1;
        }

        /**
         * 查询评论Page对象
         */
        Page<Comment> page1 = null;
        CommentService commentService = new CommentServiceImpl();
        for (int i = 0; i < page.getSize(); i++) {
            int aid = page.getList().get(i).getAid();
            page1 = commentService.getCommentPageInfo(currentPage1,aid);
            page.getList().get(i).setCommentPage(page1);
        }

        /**
         * 将page序列化为json返回给客户端
         */
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(page);
        //设置content-type防止乱码问题
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
