package com.cultureIsland.web.servlet.checkPointServlet;

import com.cultureIsland.service.CheckPointService;
import com.cultureIsland.service.UserService;
import com.cultureIsland.service.impl.CheckPointServiceImpl;
import com.cultureIsland.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 用户数据保存
 */
@WebServlet("/updateCheckPointInfo")
public class UpdateCheckPointServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * 获取闯关以及用户个人参数
         */
        int islandId = Integer.parseInt(req.getParameter("islandId"));
        String cpNum = req.getParameter("cpNum");
        HttpSession session = req.getSession();
        String userId = (String) session.getAttribute("userId");
        UserService userService = new UserServiceImpl();
        int uid = userService.getUidByUserId(userId);

        /**
         * 获取数据库储存中的闯关信息再通过从前端获取到的相关参数进行数据修改再重新存储到数据库中
         */
        CheckPointService checkPointService = new CheckPointServiceImpl();
        String checkPointInfo = checkPointService.getCheckPointInfoByUid(uid);
        String[] cpNums = checkPointInfo.split(",");
        /**
         * 当闯关数大于记录数才进行保存
         */
        if (Integer.parseInt(cpNum) > Integer.parseInt(cpNums[islandId - 1])) {
            cpNums[islandId - 1] = cpNum;
            checkPointInfo = cpNums[0] + ",";
            int len = cpNums.length - 1;
            for (int i = 1; i < len; i++) {
                checkPointInfo += cpNums[i] + ",";
            }
            if (len != 0) {
                checkPointInfo += cpNums[cpNums.length - 1];
            }

            checkPointService.updateCheckPointInfoByUid(uid, checkPointInfo);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
