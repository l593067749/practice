package com.liao.practice.test01.mutithread;

public interface Mutex {
    //获取锁
    public void lock();
    //释放锁
    public void release();
}