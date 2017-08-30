package net.duanlang.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Author: dh
 * Time: 2017/8/30 7:30
 * Description:权限配置
 **/
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    /**
     * 基于内存验证
     * @param auth
     * @throws Exception
     */
    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication().withUser("user_user").password("123456").roles("USER");
        auth.inMemoryAuthentication().withUser("user_admin").password("123456").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("user_dba_admin").password("123456").roles("ADMIN","DBA");
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeRequests().antMatchers("/","/home").permitAll()//允许所有人访问
                 .antMatchers("/admin/**").access("ADMIN")//允许管理员访问
                .antMatchers("/db/**").access("hasRole('DBA') and hasRole('ADMIN')")//允许所有人访问
                .and()
                .formLogin()//使用系统的登录界面  ss框架中自带的
                .and()
                .exceptionHandling()
                .accessDeniedPage("/accessDenied");//没有权限返回的请求
    }
}
