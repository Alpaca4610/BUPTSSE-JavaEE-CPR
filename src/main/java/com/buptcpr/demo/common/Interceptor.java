package com.buptcpr.demo.common;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 拦截器模板
public class Interceptor implements HandlerInterceptor {

//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
//        Object user = request.getSession().getAttribute("user");
//        if (user != null) {
//            request.getSession().setAttribute("user", user);
//            return true;
//        }
//        response.sendRedirect("/login.html");
//        return false;
//    }

}
