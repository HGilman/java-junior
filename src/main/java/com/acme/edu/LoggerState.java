package com.acme.edu;

/**
 * Created by Khasan on 03.11.2015.
 */
public abstract class LoggerState implements Closeable{

    public static final String SEP = System.lineSeparator();
    public String buffer = "";

    public Printable printer = new ConsolePrinter();

    abstract void flush();
    abstract void writeToBuffer(String string);
    abstract void setFormat(int format);
    public void close(){
        flush();
    }

}
