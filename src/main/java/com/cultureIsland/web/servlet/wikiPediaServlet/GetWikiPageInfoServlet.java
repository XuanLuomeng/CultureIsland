package com.cultureIsland.web.servlet.wikiPediaServlet;

import com.cultureIsland.pojo.Page;
import com.cultureIsland.pojo.WikiPedia;
import com.cultureIsland.service.impl.WikiPediaServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 获取wiki的分页内容
 */
@WebServlet("/wikiInfo")
public class GetWikiPageInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * 获取请求参数
         */
        String currentPageStr = req.getParameter("currentPage");
        String title = req.getParameter("title");
        System.out.println(title);

        /**
         * 处理参数
         */
        int currentPage = 0;
        if (currentPageStr != null && currentPageStr.length() > 0) {
            currentPage = Integer.parseInt(currentPageStr);
        } else {
            currentPage = 1;
        }
        if (title == null || title.length() < 0) {
            title = "";
        }

        /**
         * 查询Page对象
         */
        Page<WikiPedia> page = null;
        WikiPediaServiceImpl wikiPediaService = new WikiPediaServiceImpl();
        page = wikiPediaService.getWikePediaPageInfo(currentPage, title);

        /**
         * 将page序列化为json返回给客户端
         */
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(page);
        //设置content-type防止乱码问题
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
