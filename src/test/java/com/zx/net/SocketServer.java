package com.zx.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @program: myStudy
 * @description: Socket服务端
 * @author: zhou  xun
 * @create: 2023-10-03 23:13
 */
public class SocketServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888); //创建一个监听8888端口的服务器套接字
        System.out.println("Server started, waiting for client...");

        Socket socket = serverSocket.accept(); //等待客户端连接
        System.out.println("Client connected: " + socket.getInetAddress());//获取客户端连接地址

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            System.out.println("Received: " + inputLine);
            out.println("Echo: " +inputLine);
        }

        in.close();
        out.close();
        socket.close();
        serverSocket.close();
    }
}
