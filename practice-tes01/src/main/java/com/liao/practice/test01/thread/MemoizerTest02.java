package com.liao.practice.test01.thread;


import java.math.BigInteger;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

/**
 * Created by liaowenqiang on 2016/9/7.
 */
public class MemoizerTest02 {
    private  final static  int thradNum=30;
    public void test(int testid,CountDownLatch runningThreadNum){
        Computable<String,BigInteger> computable=new EcpensiveFunction();

        Computable<String,BigInteger> currMemoizer=null;
        switch (testid){
            case 0:
                currMemoizer=new Memoizer0<String,BigInteger>(computable);
                break;
            case 1:
                currMemoizer=new Memoizer1<String,BigInteger>(computable);
                break;
            case 2:
                currMemoizer=new Memoizer2<String,BigInteger>(computable);
                break;
            case 3:
                currMemoizer=new Memoizer3<String,BigInteger>(computable);
                break;
            default:
                return;
        }
        for (int i=0;i<thradNum;i++){
             new Thread(new MyRunableMt02(currMemoizer,runningThreadNum)).start();
        }
    }

    public long calTime(CountDownLatch runningThreadNum,int i) {
        //定义正在运行的线程数
        CountDownLatch innerThreadNum = new CountDownLatch(thradNum);
        MemoizerTest02 mt = new MemoizerTest02();
        long sartLong = System.currentTimeMillis();
        long endLong = System.currentTimeMillis();
        try {
            mt.test(i, innerThreadNum);
        } finally {
            //等待子线程都执行完了再执行主线程剩下的动作
            try {
                innerThreadNum.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            endLong = System.currentTimeMillis();
            innerThreadNum.countDown();

            System.out.println(i+"用时："+(endLong - sartLong));
            runningThreadNum.countDown();
            return endLong - sartLong;
        }
    }

    public static void main(String[] args) {

       /* MemoizerTest02 mt=new MemoizerTest02();
        //定义正在运行的线程数
        CountDownLatch runningThreadNum = new CountDownLatch(thradNum);

        long sartLong=System.currentTimeMillis();
        long endLong=System.currentTimeMillis();
        try{
            //mt.test(0,runningThreadNum);
            mt.test(1,runningThreadNum);
            *//*
            mt.test(2,runningThreadNum);
            mt.test(3,runningThreadNum);
            *//*
        }finally{
            //等待子线程都执行完了再执行主线程剩下的动作
            try {
                runningThreadNum.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            endLong=System.currentTimeMillis();
            System.out.println(endLong-sartLong);
        }*/
        MemoizerTest02 mt=new MemoizerTest02();
        long sartLong=System.currentTimeMillis();
        //定义正在运行的线程数
        CountDownLatch runningThreadNum = new CountDownLatch(4);
        for(int i=0;i<4;i++){
            mt.calTime(runningThreadNum,i);
        }
        try{
            runningThreadNum.await();
            long endLong=System.currentTimeMillis();
            System.out.println("共计："+(endLong-sartLong));
        }catch (Exception e){

        }


    }

    public class MyRunableMt02 implements Runnable{
        public Computable<String,BigInteger> go;
        //子线程记数器,记载着运行的线程数
        private CountDownLatch runningThreadNum;
        public MyRunableMt02(Computable<String,BigInteger> go,CountDownLatch runningThreadNum){
            this.go=go;
            this.runningThreadNum=runningThreadNum;
        }
        public void run() {
            try {
                //Thread.sleep(new Random().nextInt(20));
                for(int i=0;i<30;i++){
                    go.compute(new Random().nextInt(10)+"");
                }

            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                runningThreadNum.countDown();
            }
        }
    }
}
