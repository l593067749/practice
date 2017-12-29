package com.liao.practice.test01.io.tcp;

import java.io.IOException;

/**
 * 起一个serverExecutor线程池，多个client
 * Created by liaowenqiang on 2017/12/29.
 */
public class ServerExecutorTest {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    new ServerExecutor().go();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        for(int i=0;i<10;i++){
            new Thread(new Runnable() {
                Client client;
                @Override
                public void run() {
                    try {
                        new Client("client-"+Thread.currentThread().getName(),2).go();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
