package com.cultureIsland.web.servlet.commentServlet;

import com.cultureIsland.service.CommentService;
import com.cultureIsland.service.impl.CommentServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 通过cid删除评论
 */
@WebServlet("/deleteComment")
public class DeleteCommentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        CommentService commentService = new CommentServiceImpl();
        commentService.deleteCommentByCid(Integer.parseInt(req.getParameter("cid")));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        this.doGet(req, resp);
    }
}
