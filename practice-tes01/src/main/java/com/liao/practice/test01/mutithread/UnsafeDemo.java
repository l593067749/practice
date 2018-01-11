package com.liao.practice.test01.mutithread;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Unsafe做操作的是直接内存区，所以该类没有办法通过HotSpot的GC进行回收，需要进行手动回收，因此在使用此类时需要注意内存泄漏（Memory Leak）和内存溢出（Out Of Memory）。
 * 直接操作内存来赋值。想想如果线程阻塞了，你也就只能通过这样的方式去取值。
 */
public class UnsafeDemo {
 
    private int i = 0;
 
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        //获取Unsafe实例
        Field f = Unsafe.class.getDeclaredField("theUnsafe"); // Internal reference
        f.setAccessible(true);
        Unsafe unsafe = (Unsafe) f.get(null);
 
        //获取字段i在内存中偏移量
        long offset = unsafe.objectFieldOffset(UnsafeDemo.class.getDeclaredField("i"));
 
        //创建对象实例，设置字段的值
        UnsafeDemo unsafeDemo = new UnsafeDemo();
        unsafe.putInt(unsafeDemo, offset, 100);
 
        //打印结果
        System.out.println(unsafeDemo.i);
    }
}