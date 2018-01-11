package com.liao.practice.test01.mutithread;

import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport是JDK中比较底层的类，用来创建锁和其他同步工具类的基本线程阻塞原语。java锁和同步器框架的核心 AQS: AbstractQueuedSynchronizer，就是通过调用 LockSupport .park()和 LockSupport .unpark()实现线程的阻塞和唤醒 的。
 *
 * Created by liaowenqiang on 2018/1/11.
 */
public class LockSupportDemo {
    public static void main(String[] args)
    {
        Thread thread = Thread.currentThread();
        LockSupport.unpark(thread);//释放许可
        LockSupport.park();// 获取许可
        System.out.println("b");
    }
}
