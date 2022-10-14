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
 * 通过账号查询用户信息（不包含密码，盐和uid）
 */
@WebServlet("/getUserInfo")
public class GetUserInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * 参数获取
         */
        HttpSession session = req.getSession();
        String userId = String.valueOf(session.getAttribute("userId"));

        UserService userService = new UserServiceImpl();
        User user = userService.getUserAllInfoByUserId(userId);

        //将info对象序列化为json并将数据写回客户端
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(user);
        //设置content-type防止乱码问题
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
