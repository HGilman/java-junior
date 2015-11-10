package com.acme.edu.printers;

import com.acme.edu.exceptions.PrinterException;
import com.acme.edu.printers.Printer;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by Khasan on 06.11.2015.
 */
public class RemotePrinter extends Printer {

    private String charset;
    private String remoteFileName;
    private String ipAddress;
    private int port;
    private DataOutputStream os;
    private Socket socket;

    public RemotePrinter(String remoteFileName, String charset, String ipAddress, int port) {
        this.remoteFileName = remoteFileName;
        this.charset = charset;
        this.ipAddress = ipAddress;
        this.port = port;
        makeClient();
    }

    @Override
    public void print(String message) throws  IOException {
        os.writeUTF(message);
    }

    private void makeClient() {
        try {
            socket = new Socket(ipAddress, port);
            os = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}