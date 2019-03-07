package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by sunmood on 2019/3/7.
 * 通过实现WebMvcConfigurer接口来配置SpringMVC
 * 所有@Configuration注解的类都可以实现该接口
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");//只添加一个视图页面，handler不需要做其他处理操作
    }
}
