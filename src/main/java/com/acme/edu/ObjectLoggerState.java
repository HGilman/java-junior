package com.acme.edu;

/**
 * Created by Khasan on 03.11.2015.
 */
public class ObjectLoggerState extends LoggerState implements Closeable{
    @Override
    public void flush() {
        System.out.print(buffer);
        buffer = "";
    }

    @Override
    void writeToBuffer(String string) {
        buffer += "reference: " + string + SEP;
    }


    @Override
    void setFormat(int type) {
    }
}
