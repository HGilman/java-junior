package com.acme.edu;

import com.acme.edu.exceptions.PrinterException;

/**
 * Created by Khasan on 03.11.2015.
 */
public class StringLoggerState extends LoggerState {

    private int stringCounter = 1;

    @Override
    protected void writeToBuffer(String string) throws PrinterException {
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
    public void flush() throws PrinterException {

        if (stringCounter > 1) {
            printer.print("string: " + buffer + " (x" + stringCounter + ")" + SEP);
        } else {
            printer.print("string: " + buffer + SEP);
        }
        buffer = "";
        stringCounter = 1;
    }
}
