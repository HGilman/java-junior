package com.acme.edu;

import com.acme.edu.exceptions.PrinterException;
import com.acme.edu.printers.Printer;

import java.io.IOException;

/**
 * Created by Khasan on 06.11.2015.
 */
public class RemotePrinter extends Printer{

    private String charset;
    private String remoteFileName;

    public RemotePrinter(String remoteFileName, String charset) {
        this.remoteFileName = remoteFileName;
        this.charset = charset;
    }

    @Override
    public void print(String message) throws  IOException {

    }
}
