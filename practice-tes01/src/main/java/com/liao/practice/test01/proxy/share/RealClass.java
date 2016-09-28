package com.liao.practice.test01.proxy.share;

/**
 * 实际类
 */
public class RealClass implements IInterface {


    public void dealTask(String taskName) {
        System.out.println("执行["+taskName+"]中。。。");
    }

    public void stopTask(String taskName) {
        System.out.println("停止["+taskName+"]中。。。");
    }
}
