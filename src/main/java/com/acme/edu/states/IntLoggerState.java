package com.acme.edu.states;


import com.acme.edu.Logger;

import java.io.IOException;


public class IntLoggerState extends LoggerState {

    /**
     * store here sum of consequentially entered int
     * or sum of elements of int array
     */
    private long sum = 0;

    public IntLoggerState(String prefixDecor, String postfixDecor) {
        super(prefixDecor, postfixDecor);
    }

    @Override
    public void flush() throws IOException {

        if (prefixDecor == Logger.INT_DECOR && !(checkIfOverInteger())) {
            printAllPrinters(prefixDecor + sum + SEP + posfixDecor);
        } else if (prefixDecor == Logger.INT_DECOR) {
            printAllPrinters(buffer);
        } else {
            printAllPrinters(prefixDecor + buffer + posfixDecor);
        }
        sum = 0;
        buffer = "";
    }

    /**
     * @param string is actually int, vararg of int
     * or two dim int array
     */
    @Override
    public void writeToBuffer(String string) {

        if (prefixDecor == Logger.INT_DECOR) {
            int i = Integer.parseInt(string);
            sum += i;
            buffer += i + SEP;
        } else {
            buffer += string;
        }
    }

    private boolean checkIfOverInteger() {
        return (sum > Integer.MAX_VALUE) ? true : false;
    }
}
