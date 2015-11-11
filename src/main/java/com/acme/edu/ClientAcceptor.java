package com.acme.edu;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * Created by Khasan on 11.11.2015.
 */
public class ClientAcceptor implements Runnable {

    private ExecutorService threadPool = Executors.newFixedThreadPool(10);
    private ServerSocket server;
    private Future<String> future;
    private String result;

    public ClientAcceptor(ServerSocket server) {
        this.server = server;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                Socket client = server.accept();
                threadPool.submit(new ClientHandler(client));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
