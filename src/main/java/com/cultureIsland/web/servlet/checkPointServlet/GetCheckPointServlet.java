package com.cultureIsland.web.servlet.checkPointServlet;

import com.cultureIsland.service.CheckPointService;
import com.cultureIsland.service.UserService;
import com.cultureIsland.service.impl.CheckPointServiceImpl;
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
 * 获取到相关岛的任务点
 */
@WebServlet("/getCheckPointInfo")
public class GetCheckPointServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * 获取到岛的编号以及定位到用户的id编号
         */
        int islandId = Integer.parseInt(req.getParameter("islandId"));
        HttpSession session = req.getSession();
        String userId = (String) session.getAttribute("userId");
        UserService userService = new UserServiceImpl();
        int uid = userService.getUidByUserId(userId);

        /**
         * 通过uid获取到闯关相关的字符串，再通过字符串分割获取到相应分支的岛的任务点信息
         */
        CheckPointService checkPointService = new CheckPointServiceImpl();
        String checkPointInfo = checkPointService.getCheckPointInfoByUid(uid);
        String[] cpNums = checkPointInfo.split(",");
        String cpNum = cpNums[islandId - 1];

        /**
         * 将cpNum序列化为json返回给客户端
         */
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(cpNum);
        //设置content-type防止乱码问题
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
