package com.cultureIsland.web.servlet.viewServlet;

import com.cultureIsland.service.ViewService;
import com.cultureIsland.service.impl.ViewServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 获取view的url
 */
@WebServlet("/view")
public class GetViewUrlServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String vid = req.getParameter("vid");

        ViewService viewService = new ViewServiceImpl();
        String url = viewService.getUrlByVid(Integer.parseInt(vid));

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(url);
        //设置content-type防止乱码问题
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
