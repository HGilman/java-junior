package com.acme.edu;

/**
 * Created by Khasan on 03.11.2015.
 */
public abstract class LoggerState implements Closeable{

    protected static final String SEP = System.lineSeparator();
    protected String buffer = "";

    protected Printable printer = new ConsolePrinter();

    protected void flush() {
        printer.print(buffer);
        buffer = "";
    }

    protected void writeToBuffer(String string){
        buffer += "primitive: " + string + SEP;
    };

    protected void setFormat(int format){};
    public void close(){
        flush();
    }

}
