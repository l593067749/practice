package com.liao.practice.test01.proxy.dyn;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 调用处理器
 */
public class ProxyInvocationHandler implements InvocationHandler {

    //代理类持有一个委托类的对象引用
    private Object delegate;

    public ProxyInvocationHandler(Object delegate) {
        this.delegate = delegate;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object object=null;
        System.out.println("在方法执行前做一些处理");
        try {
            Thread.sleep(500);
            //利用反射机制将请求分派给委托类处理。Method的invoke返回Object对象作为方法执行结果。
            object=method.invoke(delegate, args);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println("在方法执行后做一些处理");
        }
        return object;
    }
}
