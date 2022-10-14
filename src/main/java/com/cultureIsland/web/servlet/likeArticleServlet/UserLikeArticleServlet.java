package com.cultureIsland.web.servlet.likeArticleServlet;

import com.cultureIsland.pojo.Article;
import com.cultureIsland.pojo.Page;
import com.cultureIsland.service.ArticleService;
import com.cultureIsland.service.LikeArticleService;
import com.cultureIsland.service.UserService;
import com.cultureIsland.service.impl.ArticleServiceImpl;
import com.cultureIsland.service.impl.LikeArticleServiceImpl;
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
import java.util.ArrayList;
import java.util.List;

/**
 * 获取个人点赞过的文章page信息
 */
@WebServlet("/userLikeArticle")
public class UserLikeArticleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * 获取相关内容，判断分页页码，是否模糊查询……
         */
        String currentPageStr = req.getParameter("currentPage");
        HttpSession session = req.getSession();
        String userId = (String) session.getAttribute("userId");

        UserService userService = new UserServiceImpl();
        int uid = userService.getUidByUserId(userId);

        /**
         * 处理参数(防止空指针异常)
         */
        int currentPage = 0;
        if (currentPageStr != null && currentPageStr.length() > 0) {
            currentPage = Integer.parseInt(currentPageStr);
        } else {
            currentPage = 1;
        }

        /**
         * 通过字符串分割获取点赞过的文章aid
         */
        LikeArticleService likeArticleService = new LikeArticleServiceImpl();
        String likeArray = likeArticleService.getLikeArray(uid);
        String[] likeList = likeArray.split(",");

        /**
         * 获取部分分页内容
         */
        int limitStart = (currentPage - 1) * 5;
        int limitEnd = limitStart + 5 < likeList.length - 1 ? limitStart + 5 : likeList.length - 1;
        Page<Article> page = new Page<>();
        page.setTotalCount(likeList.length);
        page.setPageSize(5);
        page.setTotalPage(likeList.length % 5 == 0 ? likeList.length / 5 : likeList.length / 5 + 1);
        ArticleService articleService = new ArticleServiceImpl();
        List<Article> articles = new ArrayList<>();

        /**
         * 获取相对页数的点赞过的文章
         */
        for (int i = limitStart; i < limitEnd; i++) {
            Article article = null;
            try {
                article = articleService.getUserLikeOrCommentedArticleByAid(Integer.parseInt(likeList[i]));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            articles.add(article);
        }
        page.setList(articles);

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
