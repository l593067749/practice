package com.liao.practice.test01.proxy.staticd;

import com.liao.practice.test01.proxy.share.IInterface;

/**
 * 代理类
 */
public class ProxyClass implements IInterface {
    private IInterface face;
    public ProxyClass(IInterface face){
        this.face=face;
    }
    public void dealTask(String taskName) {
        System.out.println("在方法执行前做一些处理");
        try {
            Thread.sleep(1000);
            face.dealTask(taskName);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println("在方法执行后做一些处理");
        }
    }

    public void stopTask(String taskName) {
        System.out.println("在方法执行前做一些处理");
        try {
            Thread.sleep(1000);
            face.stopTask(taskName);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println("在方法执行后做一些处理");
        }
    }
}
