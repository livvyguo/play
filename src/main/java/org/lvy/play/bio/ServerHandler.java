package org.lvy.play.bio;

import java.io.*;
import java.net.Socket;

/**
 * Created by livvy on 15/7/29.
 */
public class ServerHandler implements Runnable {

    public Socket getSocket() {
        return socket;
    }

    private Socket socket;

    public ServerHandler(Socket socket) {
        this.socket = socket;
        new Thread(this).start();
    }

    public void run() {
        DataInputStream input = null;
        DataOutputStream out = null;
        try {
            input = new DataInputStream(socket.getInputStream());
            String clientInput = input.readUTF();
            System.out.printf("receive the client message : %s %n", clientInput);
            out = new DataOutputStream(socket.getOutputStream());
            System.out.println("please input : \t");
            String s = new BufferedReader(new InputStreamReader(System.in)).readLine();
            out.writeUTF(s);
            out.close();
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
