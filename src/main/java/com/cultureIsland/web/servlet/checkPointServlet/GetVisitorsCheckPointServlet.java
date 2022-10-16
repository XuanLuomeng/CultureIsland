package com.cultureIsland.web.servlet.checkPointServlet;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 获取游客闯关信息
 */
@WebServlet("/getVisitorsCheckPoint")
public class GetVisitorsCheckPointServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * 获取相关信息
         */
        int islandId = Integer.parseInt(req.getParameter("islandId"));
        HttpSession session = req.getSession();
        String cpNumInfo = (String) session.getAttribute("cpNumInfo");
        if (cpNumInfo == null) {
            cpNumInfo = "0,0,0,0,0";
        }
        String[] cpNums = cpNumInfo.split(",");
        String cpNum = cpNums[islandId - 1];

        /**
         * 将cpNum序列化为json返回给客户端
         */
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(Integer.parseInt(cpNum));
        //设置content-type防止乱码问题
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
