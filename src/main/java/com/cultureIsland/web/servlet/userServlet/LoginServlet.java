package com.cultureIsland.web.servlet.userServlet;

import com.cultureIsland.pojo.User;
import com.cultureIsland.service.UserService;
import com.cultureIsland.service.impl.UserServiceImpl;
import com.cultureIsland.utils.InfoResponse;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 登录
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = new User();
        user.setUserId(req.getParameter("login_userid"));
        user.setPassword(req.getParameter("login_password"));
        UserService userService = new UserServiceImpl();
        boolean checkResult = userService.checkPassword(user.getUserId(), user.getPassword());
        if (checkResult) {
            //利用会话技术存储个人信息，以便用户访问其个人信息
            HttpSession session = req.getSession();
            session.setAttribute("userId", user.getUserId());
            new InfoResponse(resp, true, "登陆成功！");
        } else {
            new InfoResponse(resp, false, "账号或密码有误！");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        this.doPost(req, resp);
    }
}
