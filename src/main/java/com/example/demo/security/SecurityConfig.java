package com.example.demo.security;

import com.example.demo.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

/**
 * Created by sunmood on 2019/3/13.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * 配置用户角色权限
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*
        //配置存储在内存中的用户权限信息，对于测试来说这是一种方便的方法，但是当你需要添加、删除、修改用户权限信息时就需要重新启动应用
        auth.inMemoryAuthentication()
                .passwordEncoder(new BCryptPasswordEncoder())//Spring5.0之后需要指定加密方式，否则报错（There is no PasswordEncoder mapped for the id "null"）
                .withUser("sunmood")
                .password(new BCryptPasswordEncoder().encode("12345")).authorities("ROLE_USER")
                .and()
                .withUser("test")
                .password(new BCryptPasswordEncoder().encode("test123")).authorities("ROLE_USER");
        */
        auth.userDetailsService(userDetailsService()).passwordEncoder(encoder());
    }

    /**
     * 配置Spring Security对web层的处理
     * 拦截请求确保用户的权限
     * 1.一个请求前需要满足的安全条件
     * 2.配置自定义的登录页面
     * 3.让用户可以退出应用
     * 4.配置跨站请求的保护
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/design", "/orders")
                .hasRole("USER")
                .antMatchers("/", "/**").permitAll();
    }
}
