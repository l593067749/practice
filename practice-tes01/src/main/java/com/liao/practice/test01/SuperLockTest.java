package com.liao.practice.test01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SuperLockTest<E> //extends SuperLockSubTest<E>
{
    public List<E> list= Collections.synchronizedList(new ArrayList<E>());
    public synchronized boolean ifNullPut(E e)
    {
        if (!list.contains(e))
            list.add(e);
        return true;
    }

    public static void main(String[] args) throws Exception
    {
        final SuperLockTest<String> test=new SuperLockTest<String>();
        for(int i=0;i<3;i++){
            new Thread(new Runnable()
            {
                public void run()
                {
                   try
                {
                    Thread.sleep(new Random().nextInt(20));
                }
                catch (InterruptedException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                  //test.set(new Random().nextInt(10)+"");
                    for (int i=0;i<10;i++){
                        test.ifNullPut(i+"");
                    }

                    
                }
            }).start();
        }
        Thread.sleep(1000);
        System.out.println(test.list.size()+":"+test.list.toString());
    }
}
