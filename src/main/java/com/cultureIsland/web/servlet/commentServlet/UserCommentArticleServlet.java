package com.cultureIsland.web.servlet.commentServlet;

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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/userCommentArticle")
public class UserCommentArticleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * 获取相关内容，判断分页页码，是否模糊查询……
         */
        String currentPageStr = req.getParameter("currentPage");
        String currentPageStr1 = req.getParameter("currentCommentPage");
        HttpSession session = req.getSession();
        String userId = (String) session.getAttribute("userId");

        UserService userService = new UserServiceImpl();
        int uid = userService.getUidByUserId(userId);

        /**
         * 处理参数(防止空指针异常)
         */
        int currentPage = 0;
        int currentPage1 = 0;
        if (currentPageStr != null && currentPageStr.length() > 0) {
            currentPage = Integer.parseInt(currentPageStr);
        } else {
            currentPage = 1;
        }
        if (currentPageStr1 != null && currentPageStr1.length() > 0) {
            currentPage1 = Integer.parseInt(currentPageStr1);
        } else {
            currentPage1 = 1;
        }

        CommentService commentService = new CommentServiceImpl();
        List<Integer> aidList = commentService.getUserCommentedArticleByUid(uid);

        /**
         * 处理文章分页内容
         */
        Page<Article> page = new Page<>();
        int len = aidList.size();
        page.setTotalCount(len);
        if (len != 0) {
            ArticleService articleService = new ArticleServiceImpl();
            List<Article> articleList = new ArrayList<>();
            page.setTotalPage(aidList.size() % 5 == 0 ? aidList.size() / 5 : aidList.size() / 5 + 1);
            page.setSize(currentPage == page.getTotalPage() ? aidList.size() % 5 : 5);
            page.setPageSize(5);
            int limitStart = 0;
            int limitEnd = 0;
            if (currentPage == page.getTotalPage()) {
                limitStart = (currentPage - 1) * 5;
                limitEnd = limitStart + page.getSize();
            } else {
                limitStart = (currentPage - 1) * 5;
                limitEnd = limitStart + 5;
            }
            for (int i = limitStart; i < limitEnd; i++) {
                Article article = new Article();
                try {
                    article = articleService.getUserLikeOrCommentedArticleByAid(aidList.get(i));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                articleList.add(article);
            }
            page.setList(articleList);
            Page<Comment> page1 = new Page<>();
            for (int i = 0; i < page.getSize(); i++) {
                int aid = page.getList().get(i).getAid();
                page1 = commentService.getCommentPageInfo(currentPage1, aid);
                page.getList().get(i).setCommentPage(page1);
            }
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
