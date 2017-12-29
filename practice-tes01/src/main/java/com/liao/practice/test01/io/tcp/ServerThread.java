package com.liao.practice.test01.io.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Objects;

/**
 * Created by liaowenqiang on 2017/12/29.
 */
public class ServerThread implements Runnable {
    private Socket socket;
    public ServerThread(Socket socket) {
        this.socket=socket;
    }

    @Override
    public void run() {
        PrintStream write=null;
        BufferedReader read=null;
        try{
            write=new PrintStream(socket.getOutputStream());
            read=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            boolean flag=true;
            while (flag){
                String message=read.readLine();
                if(Objects.isNull(message)){
                    message="bye";
                }
                if(Objects.equals("bye",message)){
                    flag=false;
                }else {
                    write.println("echo:"+message);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            write.close();
            try {
                read.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("--------------断开连接----------");
        }
    }
}
