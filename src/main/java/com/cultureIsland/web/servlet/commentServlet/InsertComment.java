package com.cultureIsland.web.servlet.commentServlet;

import com.cultureIsland.pojo.Comment;
import com.cultureIsland.service.CommentService;
import com.cultureIsland.service.UserService;
import com.cultureIsland.service.impl.CommentServiceImpl;
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

@WebServlet("/insertComment")
public class InsertComment extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * 获取参数，并获取当前时间
         */
        String content = req.getParameter("content");
        int aid = Integer.parseInt(req.getParameter("aid"));
        Date pushdate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        /**
         * 获取当前用户uid信息,并用该uid去上传文章
         */
        HttpSession httpSession = req.getSession();
        Object user = httpSession.getAttribute("userId");
        String userId = (String) user;
        UserService userService = new UserServiceImpl();
        int uid = userService.getUidByUserId(userId);

        Comment comment = new Comment();
        comment.setContent(content);
        comment.setAid(aid);
        comment.setDate(simpleDateFormat.format(pushdate));
        comment.setUid(uid);
        CommentService commentService = new CommentServiceImpl();
        commentService.insertCommentByUidAndAid(comment);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
