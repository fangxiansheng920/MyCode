package org.example.WallpaperManagement.filter;


import org.example.WallpaperManagement.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"*.action"})
public class SessionFilter implements Filter {

    //所有请求都先进来，所有响应都会从我这儿出去
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String uri = req.getRequestURI();
        System.out.println(uri);
        if (uri.contains("/login.html") || uri.contains("/signin.html")) {
            chain.doFilter(request, response); //登录页面和注册页面可以直接放行（直接访问）
        } else {
            HttpSession session = req.getSession();
            //获取标记，标记中有User对象信息，标记是User类型，所以要强转
            User user = (User) session.getAttribute("user");
            if (user == null) {
                //未登录，回到登录页面，登录后才能放行（访问）
                resp.setStatus(401); // 401在js/global里
            } else {
                //已登录，可以直接放行（直接访问）
                chain.doFilter(request, response);
            }
        }
    }
}
