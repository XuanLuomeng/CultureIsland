package com.cultureIsland.web.servlet.checkPointServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/visitorsSave")
public class VisitorsSaveServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * 获取相关信息
         */
        int islandId = Integer.parseInt(req.getParameter("islandId"));
        String cpNum = req.getParameter("cpNum");
        HttpSession session = req.getSession();
        String cpNumInfo = (String) session.getAttribute("cpNumInfo");

        /**
         * 通过字符串分割和拼接修改闯关信息
         */
        if (cpNumInfo.length() == 0 || cpNumInfo == null) {
            cpNumInfo = "0,0,0,0,0";
        }
        String[] cpNums = cpNumInfo.split(",");
        cpNums[islandId] = cpNum;
        for (int i = 0; i < cpNums.length - 1; i++) {
            cpNumInfo += cpNums[i] + ",";
        }
        cpNumInfo += cpNums[cpNums.length - 1];

        /**
         * 重新修改session中闯关信息
         */
        session.setAttribute("cpNumInfo", cpNumInfo);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
