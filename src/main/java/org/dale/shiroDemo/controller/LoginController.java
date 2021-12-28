package org.dale.shiroDemo.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Administrator on 2021/12/16.
 * Description:
 */
@RestController
public class LoginController {
    @PostMapping(value = "/doLogin",produces = "text/html;charset=utf-8")
    public String doLogin(String username,String password){
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        try {
            //执行登录
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            List list = subject.getPrincipals().asList();
            for (Object o : list) {
                System.out.println(o.getClass()+"------------------->"+o);
            }
        }catch (AuthenticationException e){
            return "登录失败" + e.getMessage();
        }
        return "success";
    }
}
