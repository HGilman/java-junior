package com.acme.edu;

import com.acme.edu.exceptions.PrinterException;


/**
 *
 */
public abstract class LoggerState implements Closeable {


    protected static final String SEP = System.lineSeparator();

    /**
     * Store here sequence of numbers or string or arrays presentation
     */
    protected String buffer = "";

    /**
     * We gonna use console printer to write to console
     */
    protected Printable printer = new ConsolePrinter();
    protected int format;

    /**
     * Call this if state is changed or if close() method was called
     * to clear buffer and other fields on LoggerState realization classes
     */
    protected void flush() throws PrinterException {
        printer.print(buffer);
        buffer = "";
    }

    /**
     * Adds new message to buffer.
     */
    protected void writeToBuffer(String string) throws PrinterException{
        buffer += "primitive: " + string + SEP;
    }

    /**
     * Use it if your LoggerState class realization has local format.
     * @param format
     */
    protected void setFormat(int format) {
        this.format = format;
    }

    /**
     * I call this in the beginning of any log() method
     * to change state , flush  buffer and
     * then set new state and new format
     */
    protected LoggerState switchToNewState(LoggerState newState, int format) throws PrinterException {
        if (!(this == newState) && !this.buffer.equals("")) {
            this.flush();
        }
        newState.setFormat(format);
        return newState;
    }

    /**
     * You should call this after last call of any log() method
     * @throws PrinterException in case of problems while writing messages
     */
    @Override
    public void close() throws PrinterException {
        flush();
    }

}
