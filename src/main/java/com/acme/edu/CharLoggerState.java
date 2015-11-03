package com.acme.edu;

/**
 * Created by Khasan on 03.11.2015.
 */
public class CharLoggerState extends LoggerState implements Closeable{


    @Override
    void flush() {
        printer.print( buffer );
        buffer = "";
    }

    @Override
    void writeToBuffer(String string) {
        buffer +="char: " + string + SEP;
    }

    @Override
    void setFormat(int format) {

    }


}






