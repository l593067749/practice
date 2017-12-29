package com.liao.practice.test01.io.tcp.customizeprotocol;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by meishan on 16/12/1.
 */
public class Server {

    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(9091);
            while (true) {
                Socket client = server.accept();
                System.out.println("客户端" + client.getRemoteSocketAddress() + "连接成功");
                parseProtocol(client);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 消息解析
     *
     * @param client
     * @throws IOException
     */
    private static void parseProtocol(Socket client) throws IOException {
        InputStream is = client.getInputStream();
        DataInputStream dis = new DataInputStream(is); //读取Java标准数据类型的输入流

        //协议解析
        while (true) {
            byte type = dis.readByte();               //读取消息类型
            int totalLen = dis.readInt();             //读取消息长度
            byte[] data = new byte[totalLen - 4 - 1]; //定义存放消息内容的字节数组
            dis.readFully(data);                      //读取消息内容
            String msg = new String(data);            //消息内容

            System.out.println("接收消息类型" + type);
            System.out.println("接收消息长度" + totalLen);
            System.out.println("发来的内容是:" + msg);
        }
    }
}