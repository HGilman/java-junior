package com.acme.edu.states;


import java.io.IOException;

/**
 * Created by Khasan on 03.11.2015.
 */
public class StringLoggerState extends LoggerState {

    private int stringCounter = 1;

    public StringLoggerState(String prefixDecor, String postfixDecor) {
        super(prefixDecor, postfixDecor);
    }

    @Override
    public void writeToBuffer(String string) throws IOException {
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
            printAllPrinters(prefixDecor + buffer + " (x" + stringCounter + ")" + SEP + posfixDecor);
        } else {
            printAllPrinters(prefixDecor + buffer + SEP + posfixDecor);
        }
        buffer = "";
        stringCounter = 1;
    }
}
