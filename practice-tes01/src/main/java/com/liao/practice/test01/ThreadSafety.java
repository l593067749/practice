package com.liao.practice.test01;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

/**
 * Created by liaowenqiang on 2016/9/8.
 */
public class ThreadSafety {
    private  final static  int thradNum=20;

    /**
     * 根据容器id获取容器
     * @param memonizerId
     * @return
     * @throws Exception
     */
    public Computable<String,String> getMemoizer(int memonizerId) throws  Exception{
        Computable<String,String> computable=new EcpensiveFunction2();
        Computable<String,String> currMemoizer=null;
        switch (memonizerId){
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

        return currMemoizer;
    }

    public long calTime(CountDownLatch memoizerDownLatch,int memonizerId) {
        //内线程运行次数
        CountDownLatch innerThreadNum = new CountDownLatch(thradNum);
        ThreadSafety mt = new ThreadSafety();
        Computable<String,String> currMemoizer=null;
        long sartLong = System.currentTimeMillis();
        long endLong = System.currentTimeMillis();
        try {
             currMemoizer=mt.getMemoizer(memonizerId);
            for (int i=0;i<thradNum;i++){
                new Thread(new ThreadSafety.ThreadSafetyRunableMt04(currMemoizer,innerThreadNum,i)).start();
            }
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
            if(currMemoizer.toString().indexOf("null")>0){
                try{
                 System.out.println("["+memonizerId+"]用时："+(endLong - sartLong)+"->"+currMemoizer.toString());
                }catch (Exception e){
                    e.printStackTrace();
                }

            }

            //容器DownLatch-1
            memoizerDownLatch.countDown();
            return endLong - sartLong;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        //容器运行数
        int cycleNum=50;
        for(int i=0;i<cycleNum;i++){//多循环几次，展示效果
           // System.out.println("第"+i+"次------------------------------------------------------");
            ThreadSafety mt=new ThreadSafety();
            long sartLong=System.currentTimeMillis();

            //容器运行数
            CountDownLatch memoizerDownLatch = new CountDownLatch(1);
            //for(int i=0;i<runingNum;i++){
            mt.calTime(memoizerDownLatch,4);
            //}
            try{
                memoizerDownLatch.await();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                long endLong=System.currentTimeMillis();
               // System.out.println(i+"->共计："+(endLong-sartLong));
            }
        }

    }


    public class ThreadSafetyRunableMt04 implements Runnable{
        public Computable<String,String> go;
        //子线程记数器,记载着运行的线程数
        private CountDownLatch runningThreadNum;
        private int count;
        public ThreadSafetyRunableMt04(Computable<String,String> go,CountDownLatch runningThreadNum,int i){
            this.go=go;
            this.runningThreadNum=runningThreadNum;
            this.count=i;
        }
        public void run() {
            try {
                //Thread.sleep(new Random().nextInt(20));
                for(int i=(count)*2500;i<2500*(count+1);i++){
                    //Thread.sleep(new Random().nextInt(20));
                    go.compute(i+"");
                }

            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                //每次线程运行结束后减一
                runningThreadNum.countDown();
            }
        }
    }
}
