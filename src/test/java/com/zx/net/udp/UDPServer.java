package com.zx.net.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * @program: myStudy
 * @description: UDP服务端
 * @author: zhou  xun
 * @create: 2023-10-04 00:38
 */
public class UDPServer {
    public static void main(String[] args) {
        //创建服务器端DatagramSocket，指定端口号
        try (DatagramSocket serverSocket = new DatagramSocket(8800)) {
            //定义接收数据最大长度
            byte[] data = new byte[1024];
            //创建DatagramPacket，用于接收数据
            DatagramPacket receivePacket = new DatagramPacket(data, data.length);

            do {
                //接收数据
                serverSocket.receive(receivePacket);
                //获取客户端发送的数据
                String info = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Received from client: " + info);

                //发送数据到客户端
                InetAddress address = receivePacket.getAddress();
                int port = receivePacket.getPort();
                String sentence = "Hello from server";
                byte[] data2 = sentence.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(data2, data2.length, address, port);
                serverSocket.send(sendPacket);
            } while (true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
