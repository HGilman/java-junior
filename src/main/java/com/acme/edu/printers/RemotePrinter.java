package com.acme.edu.printers;

import com.acme.edu.exceptions.PrinterException;
import com.acme.edu.printers.Printer;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Created by Khasan on 06.11.2015.
 */
public class RemotePrinter extends Printer {

    private String ipAddress;
    private int port;
    private BufferedWriter os;
    private Socket socket;

    public RemotePrinter(String ipAddress, int port) {
        this.ipAddress = ipAddress;
        this.port = port;
    }

    @Override
    public void print(String message) throws  IOException {
        makeClient();
        os.write(message);
        os.flush();
        os.close();
    }

    private void makeClient() {
        try {
            socket = new Socket(ipAddress, port);
            os = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}