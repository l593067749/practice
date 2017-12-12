package com.liao.practice.test01.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class ChannelAccept {
    public static final String GREETING = "Hello I must be going.\r\n";
    public static void main(String[] argv) throws Exception {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ByteBuffer buffer = ByteBuffer.wrap(GREETING.getBytes());
                    ServerSocketChannel ssc = ServerSocketChannel.open();
                    ssc.socket().bind(new InetSocketAddress(1234));
                    ssc.configureBlocking(false);

                    boolean isRun=true;
                    while (isRun) {

                        SocketChannel sc = ssc.accept();
                        if (sc == null) {
                            System.out.println("Waiting for connections");
                            // no connections, snooze a while
                            Thread.sleep(2000);
                        } else {
                            sc.configureBlocking(false);
                            ByteBuffer allocate = ByteBuffer.allocateDirect (16 * 1024);
                            while(sc.read(allocate)>0){
                                allocate.flip();
                                while (buffer.hasRemaining( )) {
                                    byte b = buffer.get();
                                    System.out.println(b);
                                }
                                allocate.clear();
                            }

                            System.out.println("Incoming connection from: "
                                    + sc.socket().getRemoteSocketAddress());
                            buffer.rewind();
                            sc.write(buffer);
                            sc.close();
                            isRun=false;
                        }
                     }
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }).start();



        Thread.sleep(2000*3);
        SocketChannel socketChannel=SocketChannel.open(new InetSocketAddress(1234));
        socketChannel.close();
    }
}