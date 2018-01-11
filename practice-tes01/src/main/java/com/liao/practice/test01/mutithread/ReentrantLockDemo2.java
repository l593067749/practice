package com.liao.practice.test01.mutithread;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo2 {
    public static void main(String[] args) {
        ReentrantLock lock=new ReentrantLock();
        System.out.println(lock.getHoldCount());//没有调用lock之前，hold count为0
        lock.lock();//holdCount+1
        System.out.println(lock.getHoldCount());
        lock.lock();//holdCount+1
        System.out.println(lock.getHoldCount());
        lock.unlock();//holdCount-1
        System.out.println(lock.getHoldCount());
        lock.unlock();//holdCount-1
        System.out.println(lock.getHoldCount());
    }
}