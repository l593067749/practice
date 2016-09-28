package com.liao.practice.test01;


import jdk.nashorn.internal.runtime.ECMAException;

import java.math.BigInteger;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

/**
 * Created by liaowenqiang on 2016/9/7.
 */
public class MemoizerTest04 {
    private  final static  int thradNum=5;

    public Computable<String,String> test2(int testid,  CountDownLatch runningThreadNum) throws  Exception{
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
            case 4:
                currMemoizer=new Memoizer4<String,String>(computable);
                break;
            default:
                return null;
        }
        for (int i=0;i<thradNum;i++){
            new Thread(new MyRunableMt04(currMemoizer,runningThreadNum,i)).start();
        }
        return currMemoizer;
    }

    public long calTime(CountDownLatch runningThreadNum,int i) {
        //定义正在运行的线程数
        CountDownLatch innerThreadNum = new CountDownLatch(thradNum);
        MemoizerTest04 mt = new MemoizerTest04();
        Computable<String,String> cache=null;
        long sartLong = System.currentTimeMillis();
        long endLong = System.currentTimeMillis();
        try {
            cache=mt.test2(i, innerThreadNum);
        } catch (Exception e){
           e.printStackTrace();
        } finally {
            //等待子线程都执行完了再执行主线程剩下的动作
            try {
                innerThreadNum.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            endLong = System.currentTimeMillis();
            innerThreadNum.countDown();
            if(cache.toString().indexOf("null")>0){
                System.out.println(i+"用时："+(endLong - sartLong)+"->"+cache.toString());
            }


            runningThreadNum.countDown();
            return endLong - sartLong;
        }
    }

    public static void main(String[] args) {

        for(int i=0;i<200;i++){
            MemoizerTest04 mt=new MemoizerTest04();
            long sartLong=System.currentTimeMillis();
            int runingNum=1;
            //定义正在运行的线程数
            CountDownLatch runningThreadNum = new CountDownLatch(runingNum);
            //for(int i=0;i<runingNum;i++){
            mt.calTime(runningThreadNum,4);
            //}
            try{
                runningThreadNum.await();
                long endLong=System.currentTimeMillis();
                //System.out.println("共计："+(endLong-sartLong));
            }catch (Exception e){

            }
        }




    }


    public class MyRunableMt04 implements Runnable{
        public Computable<String,String> go;
        //子线程记数器,记载着运行的线程数
        private CountDownLatch runningThreadNum;
        private int count;
        public MyRunableMt04(Computable<String,String> go,CountDownLatch runningThreadNum,int i){
            this.go=go;
            this.runningThreadNum=runningThreadNum;
            this.count=i;
        }
        public void run() {
            try {
                //Thread.sleep(new Random().nextInt(20));
                for(int i=(count)*2500;i<2500*(count+1);i++){
                   Thread.sleep(new Random().nextInt(1));
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
