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

    /**
     * Call this if state is changed or if close() method was called
     * to clear buffer and other fields on LoggerState realization classes
     */
    protected void flush() throws PrinterException{
        printer.print(buffer);
        buffer = "";
    }

    /**
     * Adds new message to buffer.
     */
    protected void writeToBuffer(String string) {
        buffer += "primitive: " + string + SEP;
    }

    /**
     * Use it if your LoggerState class realization has local format.
     * @param format
     */
    protected void setFormat(int format) {
    }

    /**
     * You should call this after last call of any log() method
     * @throws PrinterException in case of problems while writing messages
     */
    public void close() throws PrinterException {
        flush();
    }

}
