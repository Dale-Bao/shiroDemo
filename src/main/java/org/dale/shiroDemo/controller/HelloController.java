package org.dale.shiroDemo.controller;

import org.dale.shiroDemo.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator on 2021/12/13.
 * Description:
 */
@RestController
public class HelloController {

    @Autowired
    private HelloService helloService;

    @GetMapping("/hello")
    public String hello(){
        return helloService.hello();
    }

    @GetMapping("/admin")
    public String admin(){
        return helloService.admin();
    }

    @GetMapping("/unauthorizedUrl")
    public String unauthorized(){
        return "unauthorized";
    }
}
