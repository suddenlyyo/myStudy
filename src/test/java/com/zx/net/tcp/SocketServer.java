package com.zx.net.tcp;

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
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8888)) {
            System.out.println("Server started, waiting for client...");
            Socket socket = serverSocket.accept(); //等待客户端连接
            System.out.println("Client connected: " + socket.getInetAddress());//获取客户端连接地址
            PrintWriter out;
            try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                out = new PrintWriter(socket.getOutputStream(), true);
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    System.out.println("Received: " + inputLine);
                    out.println("Echo: " + inputLine);
                }
            }
            out.close();
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
