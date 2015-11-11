package com.acme.edu;

import java.io.*;
import java.net.Socket;


/**
 * Created by Khasan on 11.11.2015.
 */
public class ClientHandler  implements Runnable {

    private Socket client;

    public ClientHandler(Socket client) {
        super();
        this.client = client;
    }

    @Override
    public void run()  {
        try(
                BufferedReader input = new BufferedReader(
                        new InputStreamReader(client.getInputStream()));

                BufferedWriter output = new BufferedWriter(
                        new OutputStreamWriter(
                                new FileOutputStream(Server.logFileName,true)))
        ) {
            String inString;
            while ((inString = input.readLine()) != null) {
                output.write(inString);
                output.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
