package com.cultureIsland.web.servlet.userServlet;

import com.cultureIsland.pojo.User;
import com.cultureIsland.service.UserService;
import com.cultureIsland.service.impl.UserServiceImpl;
import com.cultureIsland.utils.InfoResponse;
import com.cultureIsland.utils.RandomName;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 注册
 */
@WebServlet("/registUserServlet")
public class RegistUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ServletContext servletContext = this.getServletContext();
        String realPath = servletContext.getRealPath("/file/surname");
        String realPath1 = servletContext.getRealPath("/file/name");

        User user = new User();
        user.setUserId(req.getParameter("register_userid"));
        user.setPassword(req.getParameter("register_password"));
        String userName = req.getParameter("usernameMsg");
        user.setUserName(userName);
        if (userName == null || userName.equals("")) {
            RandomName randomName = new RandomName(realPath,realPath1);
            user.setUserName(randomName.getName());
        }
        UserService userService = new UserServiceImpl();
        boolean exitUser = userService.isExistUser(user.getUserId());
        if (exitUser) {
            new InfoResponse(resp, false, "用户已存在！");
        } else {
            userService.insertUser(user);
            new InfoResponse(resp, true, "注册成功！");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
