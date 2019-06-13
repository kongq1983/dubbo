package com.kq.dubboclient;

import com.kq.dubboclient.util.CuratorUtil;

import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.List;
import java.util.Random;

import com.alibaba.fastjson.JSON;
import com.kq.util.ByteUtil;

/**
 * DubboClient
 *
 * @author kq
 * @date 2019-06-12
 */
public class DubboClient {

    public static void main(String[] args) throws Exception{

        List<String> providers = CuratorUtil.getChildPaths("/dubbo/com.kq.api.IDemoService/providers");
        System.out.println("providers="+providers);

        int index = new Random().nextInt(providers.size());
        System.out.println("index="+index);

        System.out.println("未解码："+providers.get(index));

        String providerUrl = URLDecoder.decode(providers.get(index), "utf-8");
        System.out.println("解码："+providerUrl);

        printProviderInfo(providerUrl);


        String[] ipPort = providerUrl.split("/")[2].split(":");

        String host = ipPort[0];
        int port = Integer.valueOf(ipPort[1]);

        System.out.println("得到服务提供者实例，信息如下：" + host + ":" + port);

        send(host,port);

    }


    private static void send(String host,int port) throws Exception{

        SocketChannel dubboClient = SocketChannel.open();
        dubboClient.connect(new InetSocketAddress(host, port));

        String bodyStr = getBody();

        byte[] body = bodyStr.getBytes();

        // 发送数据 - header段
        byte[] header = new byte[16];
        // 魔数，short类型 (类似 class文件里面的cafebabb) 数据的开头
        byte[] magicArray = ByteUtil.short2bytes((short) 0xdabb);
        System.arraycopy(magicArray, 0, header, 0, 2);
        // 标志：请求/响应， 以及body数据的序列化方式
        header[2] = (byte) 0xC6;
        // 响应状态码
        header[3] = 0x00;
        // messageId(8B)，每次请求的唯一ID
        byte[] messageId = ByteUtil.long2bytes(1);
        System.arraycopy(messageId, 0, header, 4, 8);
        // bodyLength(4B)，后面的内容长度
        byte[] bodyLength = ByteUtil.int2bytes(body.length);
        System.arraycopy(bodyLength, 0, header, 12, 4);

        // 拼装请求报文
        byte[] request = new byte[body.length + header.length];
        System.arraycopy(header, 0, request, 0, header.length);
        System.arraycopy(body, 0, request, 16, body.length);


        boolean isOpen = dubboClient.isOpen();      // 测试SocketChannel是否为open状态
        boolean isConnected = dubboClient.isConnected();    //测试SocketChannel是否已经被连接
        boolean isConnectionPending = dubboClient.isConnectionPending();    //测试SocketChannel是否正在进行连接
        boolean finishConnect = dubboClient.finishConnect();    //校验正在进行套接字连接的SocketChannel是否已经完成连接

        System.out.println("isOpen="+isOpen);
        System.out.println("isConnected="+isConnected);
        System.out.println("isConnectionPending="+isConnectionPending);
        System.out.println("finishConnect="+finishConnect);


        dubboClient.write(ByteBuffer.wrap(request));

        ByteBuffer response = ByteBuffer.allocate(1025);
        System.out.println("start response length="+response.position());
        dubboClient.read(response);
        System.out.println("end response length="+response.position());
        System.out.println("响应内容：");
        System.out.println(new String(response.array()));


    }



//            "2.0.2"
//            "com.kq.api.IDemoService"
//            "0.0.0"
//            "getMessage"
//            "Ljava/lang/String;"
//            "123"
//            {}
    private static String getBody(){

        StringBuffer bodyString = new StringBuffer();
        // rpc协议的版本
        bodyString.append(JSON.toJSONString("2.0.2")).append("\r\n");
        // 接口地址
        bodyString.append(JSON.toJSONString("com.kq.api.IDemoService")).append("\r\n");
        // 接口版本
        bodyString.append(JSON.toJSONString("0.0.0")).append("\r\n");
        // 具体方法名
        bodyString.append(JSON.toJSONString("getMessage")).append("\r\n");
        // 参数描述
        bodyString.append(JSON.toJSONString("Ljava/lang/String;")).append("\r\n");
//        bodyString.append(JSON.toJSONString("java/lang/String;")).append("\r\n");
        // 参数值
        bodyString.append(JSON.toJSONString("123")).append("\r\n");
        // 附加参数(用于拓展dubbo功能的，暂缓)
        bodyString.append("{}").append("\r\n");
//        byte[] body = bodyString.toString().getBytes();
        System.out.println("body内容组装完成：");
        System.out.println(bodyString.toString());

        return bodyString.toString();

    }


    private static void printProviderInfo(String providerUrl) {

        String[] strs = providerUrl.split("\\?");

        System.out.println(strs[0]);

        String[] kvs = strs[1].split("&");

        for(String kv : kvs) {
            System.out.println(kv);
        }

    }

    public static void main2(String[] args) {
        getBody();
    }
}
