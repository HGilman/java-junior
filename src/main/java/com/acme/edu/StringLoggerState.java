package com.acme.edu;


import java.io.IOException;

/**
 * Created by Khasan on 03.11.2015.
 */
public class StringLoggerState extends LoggerState {

    private int stringCounter = 1;

    @Override
    protected void writeToBuffer(String string) throws IOException {
        if (buffer.equals(string)) {
            stringCounter++;
        } else {
            if (!buffer.equals("")) {
                flush();
            }
            buffer = string;
        }
    }

    @Override
    public void flush() throws IOException {

        if (stringCounter > 1) {
            printAllPrinters("string: " + buffer + " (x" + stringCounter + ")" + SEP);
        } else {
            printAllPrinters("string: " + buffer + SEP);
        }
        buffer = "";
        stringCounter = 1;
    }
}
