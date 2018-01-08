package com.liao.practice.test01.proxy.share;

/**
 * Created by liaowenqiang on 2016/9/12.
 */
public class ProxyTest {

    public void testStaticProxy(){
        IInterface face=ProxyFactory.getInstance();
        face.dealTask("构建工程");
    }
    public void testDynProxy(){
        IInterface face=ProxyFactory.getDynInstance();
        System.out.println("获取执行结果："+face.dealTask("构建工程"));
        System.out.println("--------------------------");
        face.stopTask("构建工程");
    }
    public static void main(String[] args) {
        ProxyTest proxyTest=new ProxyTest();
        proxyTest.testStaticProxy();
        System.out.println("--------------------------");
        proxyTest.testDynProxy();
    }
}
