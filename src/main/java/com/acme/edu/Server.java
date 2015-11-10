package com.acme.edu;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Khasan on 10.11.2015.
 */
public class Server {
    private static final int PORT = 1;

    public static void main(String[] args) {
        try (
            ServerSocket server = new ServerSocket(PORT);
            Socket client = server.accept();
            BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
        ) {
            ExecutorService service =
                    Executors.newFixedThreadPool(10);

            String inputString;
            while ((inputString = input.readLine()) != null) {
                Future<String> f = service.submit(new Message(inputString));
            }
        } catch (IOException e) {
            System.out.println("problem on server!");
        }
    }
}
