package com.acme.edu.exceptions;


/**
 * Created by Khasan on 05.11.2015.
 */
public class LoggerException extends Exception {

    public LoggerException(String s) {
        super(s);

        System.out.println("Exception in Logger " + s);
    }

    public LoggerException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoggerException(Throwable cause) {
        super(cause);
    }
}
