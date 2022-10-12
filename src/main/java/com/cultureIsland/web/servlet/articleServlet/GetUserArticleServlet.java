package com.cultureIsland.web.servlet.articleServlet;

import com.cultureIsland.pojo.Article;
import com.cultureIsland.pojo.Page;
import com.cultureIsland.service.ArticleService;
import com.cultureIsland.service.UserService;
import com.cultureIsland.service.impl.ArticleServiceImpl;
import com.cultureIsland.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/UserArticle")
public class GetUserArticleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * 获取请求参数
         */
        String currentPageStr = req.getParameter("currentPage");
        String title = "%" + req.getParameter("title") + "%";
        HttpSession httpSession = req.getSession();
        Object userId = httpSession.getAttribute("userId");
        UserService userService = new UserServiceImpl();
        int uid = userService.getUidByUserId((String) userId);

        /**
         * 处理参数
         */
        int currentPage = 0;
        if (currentPageStr != null && currentPageStr.length() > 0) {
            currentPage = Integer.parseInt(currentPageStr);
        } else {
            currentPage = 1;
        }

        /**
         * 查询Page对象
         */
        Page<Article> page = null;
        ArticleService articleService = new ArticleServiceImpl();
        page = articleService.getUserArticlePageInfo(currentPage, title, uid);

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
