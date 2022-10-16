package com.cultureIsland.web.servlet.userServlet;

import com.cultureIsland.pojo.User;
import com.cultureIsland.service.UserService;
import com.cultureIsland.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/updateUserInfo")
public class UpdateUserInfoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * 获取参数
         */
        HttpSession session = req.getSession();
        String userId = String.valueOf(session.getAttribute("userId"));
        User user = new User();
        user.setUserName(req.getParameter("userName"));
        user.setTelephone(req.getParameter("telephone"));
        user.setSex(req.getParameter("sex"));
        user.setEmail(req.getParameter("email"));
        user.setBirthday(req.getParameter("birthday"));
        user.setUserId(userId);

        UserService userService = new UserServiceImpl();
        userService.updateUserInfoByUser(user);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
