package com.acme.edu;

/**
 * Created by Khasan on 03.11.2015.
 */
public class StringLoggerState extends LoggerState {


    public static final int STRING = 0;

    private int format;

    @Override
    protected void writeToBuffer(String string) {
        buffer += "string: " + string + SEP;
    }

    @Override
    protected void setFormat(int format) {
        this.format = format;
    }
}
