package com.liao.practice.test01.proxyservice.sample01;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
 

public class Proxyd {
 
    public static void main(String[] args) {
         if(args==null||args.length==0){
             args=new String[] {"-p","888"};
         }
        if (args.length > 0 && args[0].equals("-p")) {
            int port = Integer.parseInt(args[1]);
            ServerSocket serverSocket = null;
            try {
                serverSocket = new ServerSocket(port);
                System.out.println("The proxy have start on port:" + port + "\n");
                while (true) {
                    Socket socket = null;
                    try {
                        socket = serverSocket.accept();
                        new HttpProxyMainThread(socket).start();
                    } catch (Exception e) {
                        System.out.println("Thread start fail");
                    }
                }
            } catch (IOException e1) {
                System.out.println("proxyd start fail\n");
            }finally{
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    //e.printStackTrace();
                }
            }
        }else{
            System.out.println("parameter error");
        }
    }
     
}