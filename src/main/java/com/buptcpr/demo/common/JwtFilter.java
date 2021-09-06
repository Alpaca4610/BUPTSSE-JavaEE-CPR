//package com.buptcpr.demo.common;
//
//import org.springframework.web.filter.GenericFilterBean;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class JwtFilter extends GenericFilterBean {
//    @Override
//    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
//            throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) req;
//        HttpServletResponse response = (HttpServletResponse) res;
//        if ("OPTIONS".equals(request.getMethod())) {//预请求直接通行
//            chain.doFilter(request, response);
//            return;
//        }
//        String auth = request.getHeader("token");
//        if ((auth != null) && (auth.length() > 7)) {
//            String headStr = auth.substring(0, 6).toLowerCase();
//            if (headStr.compareTo("bearer") == 0) { //按照国际惯例，客户端在发送token的时候需要告知来人，所以在token前面加上的“bearer;”,这里下步进行token校验时需要分离出来。
//                auth = auth.substring(7, auth.length());
//                System.out.println("122"+auth);
//                if (Jwt.parseJWT(auth) != null) {
//                    System.out.println("333");
//                    chain.doFilter(request, response);
//                    return;
//                }
//            }
//        }
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("application/json; charset=utf-8");
//        response.setStatus(HttpServletResponse.SC_OK);
//        response.getWriter().write("token error");  //自定义的token无效或不存在的返回异常
//        return;
//
//    }
//}