package com.liao.practice.test01.io.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by liaowenqiang on 2017/12/29.
 */
public class Server {
    public void go() throws IOException {
        ServerSocket serverSocket=null;
        try{
            serverSocket=new ServerSocket(1234);
            Socket socket=null;
            while (true){
                socket=serverSocket.accept();
                new Thread(new ServerThread(socket)).start();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            serverSocket.close();
        }
    }
}
