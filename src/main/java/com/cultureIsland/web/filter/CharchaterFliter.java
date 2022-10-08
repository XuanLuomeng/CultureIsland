package com.cultureIsland.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用过滤器来解决中文乱码问题
 */

@WebFilter("/*")
public class CharchaterFliter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse rep, FilterChain filterChain) throws ServletException, IOException {
        //将父接口转为子接口
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) rep;
        //获取请求方法
        String method = request.getMethod();
        //解决post请求中文数据乱码问题
        if (method.equalsIgnoreCase("post")) {
            request.setCharacterEncoding("utf-8");
        }
        String uri = request.getRequestURI();
        response.setCharacterEncoding("utf-8");
        //处理响应乱码
        if (uri.contains(".css") || uri.contains(".js") || uri.contains(".png")||uri.contains(".jpg")) {
            filterChain.doFilter(request, response);
        }else {
            response.setContentType("text/html;charset=utf-8");
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
