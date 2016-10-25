package com.practice.springboot.p13.config;

import com.practice.springboot.p13.common.properties.CasSettings;
import com.practice.springboot.p13.entity.User;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * 配合cs 做单点登录.
 * www.wenqiang.com 实际映射的是本机
 *
 */
public class ShiroCasRealm extends CasRealm {

    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

       /*
        * 当没有使用缓存的时候，不断刷新页面的话，这个代码会不断执行，
        * 当其实没有必要每次都重新设置权限信息，所以我们需要放到缓存中进行管理；
        * 当放到缓存中时，这样的话，doGetAuthorizationInfo就只会执行一次了，
        * 缓存过期之后会再次执行。
        */
        System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");

        String loginName = (String)super.getAvailablePrincipal(principals);

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addStringPermission("/thmleaf/*");
        return authorizationInfo;
    }

}
