package com.cultureIsland.web.servlet.userServlet;

import com.cultureIsland.service.UserService;
import com.cultureIsland.service.impl.UserServiceImpl;
import com.cultureIsland.utils.InfoResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 修改密码
 */
@WebServlet("/updatePassword")
public class UpdatePasswordServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * 获取参数
         */
        HttpSession session = req.getSession();
        String userId = String.valueOf(session.getAttribute("userId"));
        String oldPassword = req.getParameter("oldPassword");
        String newPassword = req.getParameter("newPassword");

        UserService userService = new UserServiceImpl();
        boolean isSuccessful = userService.checkPasswordAndUpdate(userId, oldPassword, newPassword);

        if (isSuccessful) {
            new InfoResponse(resp, isSuccessful, "修改成功");
        } else {
            new InfoResponse(resp, isSuccessful, "修改失败，旧密码不正确");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
