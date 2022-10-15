package com.cultureIsland.web.servlet.userServlet;

import com.cultureIsland.pojo.User;
import com.cultureIsland.service.CheckPointService;
import com.cultureIsland.service.UserService;
import com.cultureIsland.service.impl.CheckPointServiceImpl;
import com.cultureIsland.service.impl.UserServiceImpl;
import com.cultureIsland.utils.InfoResponse;
import com.cultureIsland.utils.RandomName;
import com.cultureIsland.utils.RandomPhoto;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Random;

/**
 * 注册
 */
@WebServlet("/registUserServlet")
public class RegistUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        /**
         * 获取注册信息
         * userName后续用于检测名称是否为空
         * 若userName为空，则由后端随机命名
         * 随机头像给用户
         */
        User user = new User();
        user.setUserId(req.getParameter("register_userid"));
        user.setPassword(req.getParameter("register_password"));
        String userName = req.getParameter("usernameMsg");
        user.setUserName(userName);
        ServletContext servletContext = this.getServletContext();
        if (userName == null || userName.equals("")) {
            RandomName randomName = new RandomName(servletContext.getRealPath("/file/surname"), servletContext.getRealPath("/file/name"));
            user.setUserName(randomName.getName());
        }
        RandomPhoto randomPhoto = new RandomPhoto(servletContext.getRealPath("/file/randomAvatar"));
        user.setPhoto(randomPhoto.getPhoto());

        /**
         * 首先检测用户账号是否已经存在
         * 若存在返回false给前端表示注册失败
         * 若不存在则注册用户并返回true给前端表示注册成功,同时给闯关表进行初始化
         */
        UserService userService = new UserServiceImpl();
        boolean exitUser = userService.isExistUser(user.getUserId());
        if (exitUser) {
            new InfoResponse(resp, false, "用户已存在！");
        } else {
            userService.insertUser(user);
            int uid = userService.getUidByUserId(user.getUserId());
            CheckPointService checkPointService = new CheckPointServiceImpl();
            checkPointService.insertCheckPointInfoByUid(uid);
            new InfoResponse(resp, true, "注册成功！");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
