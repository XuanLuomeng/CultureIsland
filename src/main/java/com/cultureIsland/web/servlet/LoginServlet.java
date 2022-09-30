package com.cultureIsland.web.servlet;

import com.cultureIsland.pojo.User;
import com.cultureIsland.service.UserService;
import com.cultureIsland.service.impl.UserServiceImpl;
import com.cultureIsland.utils.InfoResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        user.setUserId(req.getParameter("userId"));
        user.setPassword(req.getParameter("password"));
        UserService userService = new UserServiceImpl();
        boolean checkResult = userService.checkPassword(user.getUserId(), user.getPassword());
        if(checkResult){
            new InfoResponse(resp,true,"登陆成功！");
        }else {
            new InfoResponse(resp,false,"账号或密码有误！");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
