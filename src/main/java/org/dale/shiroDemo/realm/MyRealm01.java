package org.dale.shiroDemo.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.dale.shiroDemo.model.User;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator on 2021/12/16.
 * Description:
 */

/**
 * 继承authenticatingRealm 可以实现认证功能
 */
public class MyRealm01 extends AuthenticatingRealm {

    private final Map<String,User> users = new HashMap<String,User>();

    public MyRealm01(){
        User u1 = new User();
        u1.setId(1);
        u1.setUsername("zhangsan");
        u1.setPassword("123");
        users.put("zhangsan",u1);
        User u2 = new User();
        u2.setId(2);
        u2.setUsername("lisi");
        u2.setPassword("123");
        users.put("lisi",u2);
    }

    /**
     * 根据用户输入的用户名去数据库查询用户信息并返回
     * @param authenticationToken 包含用户的登录时输入的用户名密码等信息
     * @return 返回值时从数据库中查询到的用户信息
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        //获取登录用户名
        String username = usernamePasswordToken.getUsername();
        User user = getFromDB(username);
        if(user == null ){
            throw new UnknownAccountException("用户名不正确");
        }
        return new SimpleAuthenticationInfo(user.getUsername(),user.getPassword(),getName());
    }

    private User getFromDB(String username) {
         return this.users.get(username);
    }
}
