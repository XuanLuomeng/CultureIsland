package com.cultureIsland.web.servlet.likeArticleServlet;

import com.cultureIsland.service.LikeArticleService;
import com.cultureIsland.service.UserService;
import com.cultureIsland.service.impl.LikeArticleServiceImpl;
import com.cultureIsland.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 点赞保存功能
 */
@WebServlet("/likeArticle")
public class LikeArticleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * 获取参数
         */
        String aid = req.getParameter("aid");
        HttpSession httpSession = req.getSession();
        Object userId = httpSession.getAttribute("userId");
        UserService userService = new UserServiceImpl();
        int uid = userService.getUidByUserId((String) userId);

        /**
         * 通过uid获取用户原有的点赞aid列表，然后用字符串拼接方法将前端点赞的aid拼接到原点赞列表并保存到数据库中
         */
        LikeArticleService likeArticleService = new LikeArticleServiceImpl();
        String likeArray = likeArticleService.getLikeArray(uid);
        likeArray += aid + ",";
        likeArticleService.updateLikeArray(uid, likeArray);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
