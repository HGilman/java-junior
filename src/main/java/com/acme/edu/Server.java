package com.acme.edu;

import java.io.*;
import java.net.BindException;
import java.net.ServerSocket;

/**
 * Created by Khasan on 10.11.2015.
 */
public class Server {
    public static String logFileName = "serverLog.txt";
    private  int port = 9999;
    private ServerSocket server;

    Thread acceptorThread;

    public Server(String logFileName) {
        this.logFileName = logFileName;
    }

    public int getPort() {
        return port;
    }

    public  void start() {
        try {
            server = new ServerSocket(port);
            acceptorThread = new Thread(new ClientAcceptor(server));
            acceptorThread.start();
        } catch (BindException e) {
            port = Math.round((float)Math.random()*1000) + 1000;
            start();
        } catch (IOException e) {
            System.out.println("problem on server!");
            try {
                server.close();
            } catch (IOException e1) {
                System.out.println("Problem while closing server Socket");
            }
        }
    }

    public void stop() {
        acceptorThread.interrupt();
    }
}
