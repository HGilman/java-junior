package com.acme.edu;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.concurrent.Callable;

/**
 * Created by Khasan on 10.11.2015.
 */
public class Message implements Callable, Runnable {

    private String message;

    public Message(String message) {
        this.message = message;
    }

    @Override
    public Object call() throws Exception {
        return new Exception();
    }

    @Override
    public void run() {
        try(
        BufferedWriter output = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream("serverLog.txt",true)));
        ) {
            output.write(message);
        } catch (IOException e) {
            System.out.println("exception in run method");
        }
    }
}
