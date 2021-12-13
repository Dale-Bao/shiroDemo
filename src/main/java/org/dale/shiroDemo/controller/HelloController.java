package org.dale.shiroDemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator on 2021/12/13.
 * Description:
 */
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello(){
        return "hello shiro";
    }
}
