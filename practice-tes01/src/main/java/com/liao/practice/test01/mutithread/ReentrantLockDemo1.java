package com.liao.practice.test01.mutithread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo1 {
    public static void main(String[] args) {
        Lock lock=new ReentrantLock();
        new Thread("Thread A"){
            @Override
            public void run() {
                lock.lock();//加锁
                try{
                    work();//work
                    }finally {
                    lock.unlock();//释放锁
                }
 
            }
        }.start();
        new Thread("Thread B"){
            @Override
            public void run() {
                lock.lock();//加锁
                try{
                    work();//work
                    }finally {
                    lock.unlock();//释放锁
                }
 
            }
        }.start();
    }
 
    public static void work(){
        try {
            System.out.println(Thread.currentThread().getName()+" started to work,currrentTime:"+System.currentTimeMillis());
            Thread.currentThread().sleep(1000);
            System.out.println(Thread.currentThread().getName()+" wnd work,currrentTime:"+System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}