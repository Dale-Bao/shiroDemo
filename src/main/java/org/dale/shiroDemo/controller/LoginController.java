package org.dale.shiroDemo.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.dale.shiroDemo.model.RespBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Administrator on 2021/12/16.
 * Description:
 */
@Controller
public class LoginController {
    @RequestMapping("/login")
    public String login(HttpServletRequest req, Model model){
        String shiroLoginFailure = (String) req.getAttribute("shiroLoginFailure");
        if(UnknownAccountException.class.getName().equals(shiroLoginFailure) || IncorrectCredentialsException.class.getName().equals(shiroLoginFailure)){
            //用户名密码输入有误
            model.addAttribute("error","用户名或密码输入有误");
        }
    return "01";
    }

    @PostMapping(value = "/doLogin",produces = "text/html;charset=utf-8")
    public String doLogin(String username, String password, HttpServletRequest req){
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        //remember功能，可前端传参配置
        token.setRememberMe(true);
        try {
            //执行登录
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            List list = subject.getPrincipals().asList();
            for (Object o : list) {
                System.out.println(o.getClass()+"------------------->"+o);
            }
        }catch (AuthenticationException e){
//            return "登录失败" + e.getMessage();
            req.setAttribute("error",e.getMessage());
            return  "forward:/01";
        }
//        return "success";
        return  "redirect:/index";
    }

    @RequestMapping("/01")
    public String m01(){
        return "01";
    }
    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/logout11")
    @ResponseBody
    public RespBean logout(){
        try{
            SecurityUtils.getSubject().logout();
            return RespBean.ok("注销成功");
        }catch (Exception e){
            e.printStackTrace();
        }
        return RespBean.error("注销失败");
    }
}
