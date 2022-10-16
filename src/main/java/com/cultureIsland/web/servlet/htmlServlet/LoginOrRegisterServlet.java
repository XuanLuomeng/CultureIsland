package com.cultureIsland.web.servlet.htmlServlet;

import com.cultureIsland.utils.HtmlTransmission;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * 模拟网址，解决直接访问html文件时的网址不美观以及网址过于固定不可改的问题
 */
@WebServlet("/loginOrRegister")
public class LoginOrRegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        new HtmlTransmission(req,resp,"/html/register.html");
    }
}