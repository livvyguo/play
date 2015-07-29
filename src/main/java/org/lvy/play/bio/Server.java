package org.lvy.play.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by livvy on 15/7/29.
 */
public class Server {

    public static void main(String[] args) throws IOException {
        System.out.println("server is start ");
        ServerSocket serverSocket = new ServerSocket(9911);
        while (true) {
            Socket socket = serverSocket.accept();
            new ServerHandler(socket);
        }
    }
}
