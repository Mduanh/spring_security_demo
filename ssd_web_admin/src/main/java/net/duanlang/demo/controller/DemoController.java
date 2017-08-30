package net.duanlang.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created with IntelliJ IDEA.
 * Author: dh
 * Time: 2017/8/30 8:03
 * Description:
 **/
@Controller
public class DemoController {
    @RequestMapping("/home")
    public String home(ModelAndView modelAndView){
        modelAndView.addObject("greeting","你好，欢迎访问");
        return "home";
    }
    @RequestMapping("/admin")
    public String admin(ModelAndView modelAndView){
        modelAndView.addObject("admin","你好，admin");
        return "admin";
    }
    @RequestMapping("/dba")
    public String dba(ModelAndView modelAndView){
        modelAndView.addObject("dba","你好，dba");
        return "dba";
    }
    @RequestMapping("/accessDenied")
    public String accessDenied(ModelAndView modelAndView){
        modelAndView.addObject("accessDenied","你好，"+getPrincipal()+"权限不足");
        return "accessDenied";
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
