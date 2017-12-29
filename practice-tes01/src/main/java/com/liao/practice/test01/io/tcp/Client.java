package com.liao.practice.test01.io.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * Created by liaowenqiang on 2017/12/29.
 */
public class Client  {
    private String name;
    private int number=10;
    public Client(String name){
        this.name=name;
    }
    public Client(String name,int number){
        this.name=name;
        this.number=number;
    }
    public void go() throws Exception {
        Socket client=new Socket("127.0.0.1",1234);
        client.setSoTimeout(5000);
        PrintStream write=new PrintStream(client.getOutputStream());
        BufferedReader read=new BufferedReader(new InputStreamReader(client.getInputStream()));
        boolean flag=true;

        while (flag){

            if(number==0){
                flag=false;
                write.println("bye");
            }else {
                number--;
                Thread.sleep(1000*new Random().nextInt(10));
                LocalDateTime date = LocalDateTime.now();
                write.println(name+" say:"+date.format(DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss")));
                System.out.println(read.readLine());
            }


        }
        if(client!=null){
            client.close();
        }
    }
}
