package com.buptcpr.demo.common;

import com.buptcpr.demo.CprApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new Interceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/js/**", "/css/**", "/img/**", "/user/login", "/user/register", "/login.html", "/register.html");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/index.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        WebMvcConfigurer.super.addViewControllers(registry);
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //addResourceHandler：指的是对外暴露的访问路径
        //addResourceLocations：指的是内部文件放置的目录
        //通过设置spring.resources.static-locations自定义Spring boot加载前端静态资源路径
//        如果指定了拦截器，该属性有可能失效
//        需要在拦截器ResourceHandlerRegistry中通过addLocations()指定对应路径
        String directory=this.getClass().getResource("/").getPath() + "/static/images/";
        File dir = new File(directory);
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:"+dir.getAbsolutePath()+"/");
    }
}
//"D:\\term\\college_application_recommendation\\src\\main\\resources\\static\\images\\"
//classpath:"+"static\\images

