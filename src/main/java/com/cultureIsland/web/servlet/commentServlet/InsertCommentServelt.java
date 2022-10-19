package com.cultureIsland.web.servlet.commentServlet;

import com.cultureIsland.pojo.Comment;
import com.cultureIsland.service.CommentService;
import com.cultureIsland.service.UserService;
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
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 发表评论
 */
@WebServlet("/insertComment")
public class InsertCommentServelt extends HttpServlet {
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

        /**
         * 设置评论内容
         */
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setAid(aid);
        comment.setDate(simpleDateFormat.format(pushdate));
        comment.setUid(uid);

        /**
         * 保存入数据库并获取评论的主键cid
         */
        CommentService commentService = new CommentServiceImpl();
        comment = commentService.insertCommentByUidAndAid(comment);

        /**
         * 将comment序列化为json返回给客户端，方便实现实时发布
         */
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(comment);
        //设置content-type防止乱码问题
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(json);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
