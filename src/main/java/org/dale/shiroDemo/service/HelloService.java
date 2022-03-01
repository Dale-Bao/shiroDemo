package org.dale.shiroDemo.service;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.stereotype.Service;

/**
 * @author Administrator on 2022/3/1.
 * Description:
 */
@Service
public class HelloService {
    @RequiresUser
    public String hello(){
        return "hello shiro";
    }

    /**
     * value是需要的角色数组
     * logical是数组角色权限关系，and和or两种。默认是AND
     * @return
     */
//    @RequiresRoles(value = {"admin","role1"},logical = Logical.OR)
    @RequiresRoles("admin")
    public String admin(){
        return "hello admin";
    }
}
