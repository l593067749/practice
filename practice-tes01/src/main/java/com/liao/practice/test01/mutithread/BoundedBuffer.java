package com.liao.practice.test01.mutithread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition的作用是替代Object的监视器方法监视器方法（wait、notify 和 notifyAll），其主要是与Lock配合使用。
 我们知道Object类的wait、notify 和 notifyAll三个方法必须在同步代码块(synchronized)中调用，但是在java并发包中，我们使用了ReentrantLock替代了synchronized关键字，
 因此我们无法直接调用wait、notify 和 notifyAll。
 *
 * Condition 实例实质上被绑定到一个锁上。要为特定 Lock 实例获得 Condition 实例，可以使用其 newCondition() 方法。
 作为一个示例，假定有一个有界的缓冲区，它支持 put 和 take 方法。如果试图在空的缓冲区上执行 take 操作，则在某一个项变得可用之前，
 线程将一直阻塞；如果试图在满的缓冲区上执行 put 操作，则在有空间变得可用之前，线程将一直阻塞。
 我们喜欢在单独的等待 set 中保存 put 线程和 take 线程，这样就可以在缓冲区中的项或空间变得可用时利用最佳规划，一次只通知一个线程。
 可以使用两个 Condition 实例来做到这一点。
 */
class BoundedBuffer {
     final Lock lock = new ReentrantLock();
     final Condition notFull  = lock.newCondition();
     final Condition notEmpty = lock.newCondition(); 
  
     final Object[] items = new Object[100];
     int putptr, takeptr, count;
  
     public void put(Object x) throws InterruptedException {
       lock.lock();
       try {
         while (count == items.length)
           notFull.await();
         items[putptr] = x;
         if (++putptr == items.length) putptr = 0;
         ++count;
         notEmpty.signal();
       } finally {
         lock.unlock();
       }
     }
  
     public Object take() throws InterruptedException {
       lock.lock();
       try {
         while (count == 0)
           notEmpty.await();
         Object x = items[takeptr];
         if (++takeptr == items.length) takeptr = 0;
         --count;
         notFull.signal();
         return x;
       } finally {
         lock.unlock();
       }
     }
   }