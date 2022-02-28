package org.dale.shiroDemo.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.dale.shiroDemo.mapper.OmpUserMapper;
import org.dale.shiroDemo.model.OmpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Administrator on 2021/12/16.
 * Description:
 */

/**
 * 继承authenticatingRealm 可以实现认证功能
 */
@Component
public class MyRealm03 extends AuthorizingRealm {

    private final Map<String, OmpUser> users = new HashMap<String, OmpUser>();
/*
    @Autowired
    OmpUserMapper ompUserMapper;*/

    public MyRealm03(){
        OmpUser u1 = new OmpUser();
        u1.setId(1);
        u1.setUsername("zhangsan");
        u1.setPassword("271dad09d1a71f27b7aeaa27306d5e24");
        users.put("zhangsan",u1);
        OmpUser u2 = new OmpUser();
        u2.setId(2);
        u2.setUsername("lisi");
        u2.setPassword("0c1b64535abaa1e871009019c6bcde0e");
        users.put("lisi",u2);
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Set<String> roles = new HashSet<String>();
        String username = (String) principalCollection.getPrimaryPrincipal();
        if("zhangsan".equals(username)){
           roles.add("admin");
        }

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo(roles);
        return authorizationInfo;
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
//        OmpUser user = ompUserMapper.getUserByName(username);
        OmpUser user = getFromDB(usernamePasswordToken.getUsername());
        if(user == null ){
            throw new UnknownAccountException("用户名不正确");
        }
//        return new SimpleAuthenticationInfo(user.getUsername(),user.getPassword(),getName());
        //返回带盐的simpleAuthenticationInfo对象
        return new SimpleAuthenticationInfo(user.getUsername(),user.getPassword(), ByteSource.Util.bytes(user.getUsername()),getName());
    }

    private OmpUser getFromDB(String username) {
         return this.users.get(username);
    }

    @Override
    public CredentialsMatcher getCredentialsMatcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher("md5");
        matcher.setHashIterations(1024);
        return matcher;
    }
}
