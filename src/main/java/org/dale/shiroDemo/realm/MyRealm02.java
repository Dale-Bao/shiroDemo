package org.dale.shiroDemo.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.springframework.stereotype.Component;

/**
 * @author Administrator on 2021/12/27.
 * Description:
 */
@Component
public class MyRealm02 extends AuthenticatingRealm {

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        return new SimpleAuthenticationInfo("dale",token.getCredentials(),getName());
    }
}
