package com.liao.practice.test01.thread;

import java.math.BigInteger;
import java.util.Random;
import java.util.concurrent.ExecutionException;

/**
 * Created by liaowenqiang on 2016/9/7.
 */
public class MemoizerTest01 {

    public void test0(final Computable<String,BigInteger> go){
        for (int i=0;i<30;i++){
            new Thread(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(new Random().nextInt(20));
                        go.compute("10");
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
    public static void main(String[] args) {
        Computable<String,BigInteger> computable=new EcpensiveFunction();
        final Memoizer0<String,BigInteger> memoizer1=new Memoizer0<String,BigInteger>(computable);
        MemoizerTest01 mt=new MemoizerTest01();
        mt.test0(memoizer1);

    }
}
