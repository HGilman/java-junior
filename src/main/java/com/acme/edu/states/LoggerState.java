package com.acme.edu.states;


import com.acme.edu.printers.Printer;

import java.io.IOException;


public abstract class LoggerState {

    public LoggerState(String prefixDecor, String posfixDecor) {
        this.prefixDecor = prefixDecor;
        this.posfixDecor = posfixDecor;
    }

    public static final String SEP = System.lineSeparator();

    /**
     * Store here sequence of numbers or string or arrays presentation
     */
    public String buffer = "";
    public String prefixDecor;
    public String posfixDecor;

    public static Printer[] printers;

    /**
     * Call this if state is changed or if close() method was called
     * to clear buffer and other fields on LoggerState realization classes
     */
    public abstract void flush() throws IOException;

    /**
     * Adds new message to buffer.
     */
    public abstract void writeToBuffer(String string) throws IOException;


    public static void setUpPrinters(Printer[] printersArg) {
         printers = printersArg;
    }

    /**
     * I call this in the beginning of any log() method
     * to change state , flush  buffer and
     * then set new state and new format
     */
    public LoggerState switchToNewState(LoggerState newState) throws IOException {
        if (!(this.getClass() == newState.getClass() && this.buffer != "")) {
            this.flush();
            return newState;
        } else {
            return this;
        }
    }

    /**
     * You should call this after last call of any log() method
     */
    public void close() throws  IOException{
        flush();
    }

    public void printAllPrinters (String s) throws IOException {
        for (Printer p : printers) {
            p.print(s);
        }
    }
}
