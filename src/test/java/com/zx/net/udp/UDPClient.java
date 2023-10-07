package com.zx.net.udp;

import java.io.IOException;
import java.net.*;

/**
 * @program: myStudy
 * @description: UDP客户端
 * @author: zhou  xun
 * @create: 2023-10-04 00:39
 */
public class UDPClient {
    public static void main(String[] args) {
        //创建客户端DatagramSocket，指定端口号
        try (DatagramSocket clientSocket = new DatagramSocket(8800)) {
            //定义要发送的数据
            String sentence = "Hello";
            byte[] data = sentence.getBytes();
            //创建DatagramPacket，用于发送数据
            DatagramPacket sendPacket = new DatagramPacket(data, data.length, InetAddress.getByName("localhost"), 8800);

            //发送数据到服务器端
            clientSocket.send(sendPacket);
            //创建DatagramPacket，用于接收数据
            byte[] data2 = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(data2, data2.length);
            //接收服务器端发送的数据
            clientSocket.receive(receivePacket);
            //获取服务器端返回的数据并输出到控制台
            String receivedData = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Received from server: " + receivedData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
