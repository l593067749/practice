package com.liao.practice.test01;


import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

/**
 * Created by liaowenqiang on 2016/9/7.
 */
public  class EcpensiveFunction2 implements Computable<String,String> {


    public String compute(String arg) throws ExecutionException, InterruptedException {
        //Thread.sleep(new Random().nextInt(2000));
       //System.out.println(Thread.currentThread().getName()+":"+arg);
        return  arg;
    }
}
