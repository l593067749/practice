package com.liao.practice.test01.io.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by liaowenqiang on 2017/12/29.
 */
public class ServerExecutor {
    public void go() throws IOException {
        ServerSocket serverSocket=null;
        try{
            serverSocket=new ServerSocket(1234);
            Socket socket=null;
            //通过调用Executors类的静态方法，创建一个ExecutorService实例
            //ExecutorService接口是Executor接口的子接口
           // Executor service = Executors.newCachedThreadPool();
            Executor service = Executors.newFixedThreadPool(15);
            while (true){
                socket=serverSocket.accept();
                service.execute(new ServerThread(socket));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            serverSocket.close();
        }
    }
}
