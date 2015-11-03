package com.acme.edu;

/**
 * Created by Khasan on 03.11.2015.
 */
public class BooleanLoggerState extends LoggerState {
    @Override
    public void flush() {
        System.out.print(buffer);
        buffer = "";
    }

    @Override
    void writeToBuffer(String string) {
        buffer += "primitive: " + string + SEP;
    }

    @Override
    void setFormat(int type) {
    }
}
