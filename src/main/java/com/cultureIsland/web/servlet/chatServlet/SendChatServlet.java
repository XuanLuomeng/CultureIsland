package com.cultureIsland.web.servlet.chatServlet;

import com.cultureIsland.service.UserService;
import com.cultureIsland.service.impl.UserServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 获取到用户发送的信息
 */
@WebServlet("/send")
public class SendChatServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * 获取到存在cookie中的userid和前端提交的聊天内容text以及聊天室编号
         */
        HttpSession httpSession = req.getSession();
        String userId = String.valueOf(httpSession.getAttribute("userId"));
        String text = req.getParameter("text");
        String chatRoomNumber = req.getParameter("chatRoomNumber");

        /**
         * 前端传输的内容不为空的时候聊天内容才有效
         * 因聊天内容有效，则通过userid获取发送者名称
         * 通过字符串拼接将发送者名称和发送内容拼接起来
         * 再将内容保存到服务器端
         */
        if (text.length() > 0) {
            UserService userService = new UserServiceImpl();
            String userName = userService.getUserNameByUserId(userId);

            String say = userName + ":" + text + "\n";

            ServletContext context = getServletContext();
            String says;
            if (context.getAttribute("says") != null) {
                says = context.getAttribute(chatRoomNumber + "says") + say;
            } else {
                says = say;
            }
            context.setAttribute(chatRoomNumber + "says", says);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
