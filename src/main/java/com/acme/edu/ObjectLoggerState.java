package com.acme.edu;

/**
 * Created by Khasan on 03.11.2015.
 */
public class ObjectLoggerState extends LoggerState {
    @Override
    public void writeToBuffer(String string){
        buffer += "reference: " + string + SEP;
    }
}
