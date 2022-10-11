package com.cultureIsland.web.servlet.userServlet;

import com.cultureIsland.pojo.User;
import com.cultureIsland.service.UserService;
import com.cultureIsland.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 从session里获取用户信息，如果没有则返回0.可用作检测是否已登录，查看个人信息，获取个人信息等供许多功能使用
 */
@WebServlet("/seeUserInfo")
public class SeeUserInfo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        Object userId = httpSession.getAttribute("userId");
        /**
         * 将user序列化为json返回给客户端
         */
        ObjectMapper mapper = new ObjectMapper();
        //检测是否为null，如果是null则处于未登录状态，返回个us,其中userid值为0
        String json;
        String userName = "";
        if(userId==null){
            userName = "0";
            json = mapper.writeValueAsString(userName);
        }else {
            UserService userService = new UserServiceImpl();
            userName = userService.getUserNameByUserId((String) userId);
             json = mapper.writeValueAsString(userName);
        }
        //设置content-type防止乱码问题
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}