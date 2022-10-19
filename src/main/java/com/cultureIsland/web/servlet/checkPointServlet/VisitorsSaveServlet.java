package com.cultureIsland.web.servlet.checkPointServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 游客保存
 */
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
        if (cpNumInfo == null) {
            cpNumInfo = "0,0,0,0,0";
        }
        String[] cpNums = cpNumInfo.split(",");
        /**
         * 当闯关数大于记录数才进行保存
         */
        if (Integer.parseInt(cpNum) > Integer.parseInt(cpNums[islandId - 1])) {
            cpNums[islandId - 1] = cpNum;
            cpNumInfo = cpNums[0] + ",";
            int len = cpNums.length - 1;
            for (int i = 1; i < len; i++) {
                cpNumInfo += cpNums[i] + ",";
            }
            if (len != 0) {
                cpNumInfo += cpNums[len];
            }

            /**
             * 重新修改session中闯关信息
             */
            session.setAttribute("cpNumInfo", cpNumInfo);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
