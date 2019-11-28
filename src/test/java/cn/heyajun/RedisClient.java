package cn.heyajun;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Admin
 * @create 2019-10-23 20:49
 * @desc
 **/
public class RedisClient {
    ServerSocket serverSocket;
    byte[] bs=new byte[1024];
    {
        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(9876));
            //阻塞
            Socket accept=serverSocket.accept();
            //阻塞
            accept.getInputStream().read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
