package com.acme.edu;

/**
 * Created by Khasan on 03.11.2015.
 */
public class CharLoggerState extends LoggerState {

    @Override
    public void writeToBuffer(String string) {
        buffer +="char: " + string + SEP;
    }

}






