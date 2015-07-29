package org.lvy.play.bio;

import java.io.*;
import java.net.Socket;

/**
 * Created by livvy on 15/7/29.
 */
public class Client {

    public static void main(String[] args) {
        System.out.println("client is start ");

        while (true) {
            Socket socket = null;
            try {
                socket = new Socket("127.0.0.1", 9911);
                DataInputStream input = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                System.out.println("please input :\t");
                String userInput = new BufferedReader(new InputStreamReader(System.in)).readLine();
                out.writeUTF(userInput);

                String recv = input.readUTF();
                System.out.println("recv info is :" + recv);

                if ("OK".equalsIgnoreCase(recv)) {
                    System.out.println("the connection will be closed ");
                    Thread.sleep(500);
                    break;
                }
                out.close();
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (socket != null) {
                    try {
                        socket.close();
                        socket = null;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
