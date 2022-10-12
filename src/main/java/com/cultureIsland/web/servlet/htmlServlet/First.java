package com.cultureIsland.web.servlet.htmlServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

@WebServlet("/first")
public class First extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = this.getServletContext();
        String realPath = servletContext.getRealPath("/html/first.html");
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
