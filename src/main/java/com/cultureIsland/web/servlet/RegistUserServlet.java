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

@WebServlet("/registUserServlet")
public class RegistUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = new User();
        user.setUserId(req.getParameter("userId"));
        user.setPassword(req.getParameter("password"));
        user.setUserName(req.getParameter("userName"));
        user.setSex(req.getParameter("sex"));
        user.setBirthday("birthday");
        user.setTelephone("telephone");
        user.setEmail("email");
        UserService userService = new UserServiceImpl();
        boolean exitUser = userService.isExistUser(user.getUserId());
        if(exitUser){
            new InfoResponse(resp,false,"用户已存在！");
        }else {
            userService.insertUser(user);
            new InfoResponse(resp,true,"注册成功！");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
