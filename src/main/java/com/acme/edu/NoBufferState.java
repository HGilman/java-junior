package com.acme.edu;

import java.io.IOException;

/**
 * Class is used , when we doesn't have to use buffer
 * like in case of int state or string state
 */
public class NoBufferState extends LoggerState {

    public static final int OBJECT_STATE = 0;
    public static final int CHAR_STATE = 1;
    public static final int BOOLEAN_STATE = 2;


    @Override
    protected void writeToBuffer(String string) throws IOException {
        switch (format) {
            case OBJECT_STATE:
                buffer += "reference: " + string + SEP;
                break;
            case CHAR_STATE:
                buffer +="char: " + string + SEP;
                break;
            case BOOLEAN_STATE:
                super.writeToBuffer(string);
        }
    }
}
