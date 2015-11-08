package com.acme.edu.states;

import java.io.IOException;

/**
 * Class is used , when we doesn't have to use buffer
 * like in case of int state or string state
 */
public class OtherState extends LoggerState {

    public OtherState(String prefixDecor, String postfixDecor) {
        super(prefixDecor, postfixDecor);
    }


    @Override
    public void flush() throws IOException {
        printAllPrinters(buffer);
        buffer = "";
    }

    @Override
    public void writeToBuffer(String string) throws IOException {
        buffer += prefixDecor + string + posfixDecor;
    }
}
