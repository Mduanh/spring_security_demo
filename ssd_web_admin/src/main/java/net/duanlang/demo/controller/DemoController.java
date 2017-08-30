package net.duanlang.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Author: dh
 * Time: 2017/8/30 8:03
 * Description:
 **/
@Controller
public class DemoController {
    @RequestMapping("/home")
    public String home(ModelMap modelMap){
        modelMap.addAttribute("home","你好，欢迎访问");
        return "home";
    }
    @RequestMapping("/admin")
    public String admin(ModelMap modelMap){
        modelMap.addAttribute("admin","你好，admin");
        return "admin";
    }
    @RequestMapping("/dba")
    public String dba(ModelMap modelMap){
        modelMap.addAttribute("dba","你好，dba");
        return "dba";
    }
    @RequestMapping("/accessDenied")
    public String accessDenied(ModelMap modelMap){
        modelMap.addAttribute("accessDenied","你好，"+getPrincipal()+"权限不足");
        return "accessDenied";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response,ModelMap modelMap){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null){
            new SecurityContextLogoutHandler().logout(request,response,authentication);
        }
        modelMap.addAttribute("home","注销成功");
        return "home";
    }

    private String getPrincipal() {
        String userName=null;
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        Object principal=authentication.getPrincipal();
        if(principal instanceof UserDetails){
            UserDetails userDetails=(UserDetails)principal;
            userName=userDetails.getUsername();
        }else{
            userName=principal.toString();
        }
        return userName;
    }

}
