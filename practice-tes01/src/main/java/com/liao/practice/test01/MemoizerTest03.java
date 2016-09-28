package com.liao.practice.test01;


import java.math.BigInteger;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

/**
 * Created by liaowenqiang on 2016/9/7.
 */
public class MemoizerTest03 {
    private  final static  int thradNum=2;
    public Computable<String,BigInteger> test(int testid,CountDownLatch runningThreadNum){
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
                return null;
        }
        for (int i=0;i<thradNum;i++){
             new Thread(new MyRunableMt03(currMemoizer,runningThreadNum)).start();
        }
        return currMemoizer;
    }
    public Computable<String,String> test2(int testid,CountDownLatch runningThreadNum){
        Computable<String,String> computable=new EcpensiveFunction2();

        Computable<String,String> currMemoizer=null;
        switch (testid){
            case 0:
                currMemoizer=new Memoizer0<String,String>(computable);
                break;
            case 1:
                currMemoizer=new Memoizer1<String,String>(computable);
                break;
            case 2:
                currMemoizer=new Memoizer2<String,String>(computable);
                break;
            case 3:
                currMemoizer=new Memoizer3<String,String>(computable);
                break;
            default:
                return null;
        }
        for (int i=0;i<thradNum;i++){
            new Thread(new MyRunableMt03_2(currMemoizer,runningThreadNum)).start();
        }
        return currMemoizer;
    }

    public long calTime(CountDownLatch runningThreadNum,int i) {
        //定义正在运行的线程数
        CountDownLatch innerThreadNum = new CountDownLatch(thradNum);
        MemoizerTest03 mt = new MemoizerTest03();
        Computable<String,String> cache=null;
        long sartLong = System.currentTimeMillis();
        long endLong = System.currentTimeMillis();
        try {
            cache=mt.test2(i, innerThreadNum);
        } finally {
            //等待子线程都执行完了再执行主线程剩下的动作
            try {
                innerThreadNum.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            endLong = System.currentTimeMillis();
            innerThreadNum.countDown();

            System.out.println(i+"用时："+(endLong - sartLong)+"->"+cache.toString());
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
        MemoizerTest03 mt=new MemoizerTest03();
        long sartLong=System.currentTimeMillis();
        int runingNum=1;
        //定义正在运行的线程数
        CountDownLatch runningThreadNum = new CountDownLatch(runingNum);
        for(int i=0;i<runingNum;i++){
            mt.calTime(runningThreadNum,i);
        }
        try{
            runningThreadNum.await();
            long endLong=System.currentTimeMillis();
            System.out.println("共计："+(endLong-sartLong));
        }catch (Exception e){

        }


    }

    public class MyRunableMt03 implements Runnable{
        public Computable<String,BigInteger> go;
        //子线程记数器,记载着运行的线程数
        private CountDownLatch runningThreadNum;
        public MyRunableMt03(Computable<String,BigInteger> go,CountDownLatch runningThreadNum){
            this.go=go;
            this.runningThreadNum=runningThreadNum;
        }
        public void run() {
            try {
                //Thread.sleep(new Random().nextInt(20));
                for(int i=0;i<3000;i++){
                    //Thread.sleep(new Random().nextInt(20));
                    go.compute(i+"");
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
    public class MyRunableMt03_2 implements Runnable{
        public Computable<String,String> go;
        //子线程记数器,记载着运行的线程数
        private CountDownLatch runningThreadNum;
        public MyRunableMt03_2(Computable<String,String> go,CountDownLatch runningThreadNum){
            this.go=go;
            this.runningThreadNum=runningThreadNum;
        }
        public void run() {
            try {
                //Thread.sleep(new Random().nextInt(20));
                for(int i=0;i<3000;i++){
                    Thread.sleep(new Random().nextInt(20));
                    go.compute(i+"");
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
