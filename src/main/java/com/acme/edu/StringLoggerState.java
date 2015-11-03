package com.acme.edu;

/**
 * Created by Khasan on 03.11.2015.
 */
public class StringLoggerState extends LoggerState implements Closeable {


    public static final int STRING = 0;

    private int format;

    @Override
    void flush() {
        System.out.print(buffer);
        buffer = "";
    }

    @Override
    void writeToBuffer(String string) {
        buffer += "string: " + string + SEP;
    }

    @Override
    void setFormat(int format) {
        this.format = format;
    }
}
