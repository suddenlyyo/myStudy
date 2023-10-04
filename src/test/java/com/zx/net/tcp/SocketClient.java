package com.zx.net.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @program: myStudy
 * @description: Socket客户端
 * @author: zhou  xun
 * @create: 2023-10-03 23:25
 */
public class SocketClient {
    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("localhost", 8888)) {
            System.out.println("Connected to server");
            PrintWriter out;
            try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                out = new PrintWriter(socket.getOutputStream(), true);
                out.println("Hello, Server!");
                String response = in.readLine();
                System.out.println("Received: " + response);
            }
            out.close();
        }
    }
}
