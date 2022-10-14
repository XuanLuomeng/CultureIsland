package com.cultureIsland.web.servlet.htmlServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * 模拟网址，解决直接访问html文件时的网址不美观以及网址过于固定不可改的问题
 */
@WebServlet("/login")
public class loginOrRegistServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = this.getServletContext();
        String realPath = servletContext.getRealPath("/html/register.html");
        PrintWriter writer = resp.getWriter();
        InputStream in = new FileInputStream(realPath);
        byte[] bys = new byte[1024];
        int len;
        while ((len = in.read(bys)) != -1) {
            writer.write(new String(bys, 0, len, "utf-8"));
        }
        in.close();
        writer.close();
    }
}