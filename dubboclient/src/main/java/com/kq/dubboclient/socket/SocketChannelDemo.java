package com.kq.dubboclient.socket;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * SocketChannelDemo
 *
 * @author kq
 * @date 2019-06-13
 */
public class SocketChannelDemo {

    public static void main(String[] args) throws Exception{

        SocketChannel dubboClient = SocketChannel.open();
        dubboClient.connect(new InetSocketAddress("localhost", 8200));

        ByteBuffer buffer = ByteBuffer.allocate(100);
        buffer.put("hello".getBytes());
        dubboClient.write(buffer);

        ByteBuffer response = ByteBuffer.allocate(1025);
        dubboClient.read(response);

        System.out.println("响应内容:");
        System.out.println(new String(response.array()));


    }

    public static void main2(String[] args) throws Exception{
        SocketChannel socketChannel = SocketChannel.open(
                new InetSocketAddress("www.baidu.com", 80));
        socketChannel.configureBlocking(false);
        ByteBuffer byteBuffer = ByteBuffer.allocate(1025);
        socketChannel.read(byteBuffer);
        socketChannel.close();
        System.out.println("test end!");
        System.out.println(new String(byteBuffer.array()));
    }

}
