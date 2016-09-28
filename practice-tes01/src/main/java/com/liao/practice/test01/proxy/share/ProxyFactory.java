package com.liao.practice.test01.proxy.share;

import com.liao.practice.test01.proxy.dyn.ProxyInvocationHandler;
import com.liao.practice.test01.proxy.staticd.ProxyClass;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 生成代理对象的工厂.
 */
public class ProxyFactory {
    //客户类调用此工厂方法获得代理对象。
    //对客户类来说，其并不知道返回的是代理类对象还是委托类对象。
    public static IInterface getDynInstance() {
        IInterface delegate = new RealClass();
        InvocationHandler handler = new ProxyInvocationHandler(delegate);
        IInterface proxy = null;
        proxy = (IInterface) Proxy.newProxyInstance(
                delegate.getClass().getClassLoader(),
                delegate.getClass().getInterfaces(),
                handler);
        return proxy;
    }
    public static IInterface getInstance(){
        IInterface delegate = new RealClass();
        return new ProxyClass(delegate);
    }

}  