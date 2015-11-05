package com.acme.edu.exceptions;


/**
 * Created by Khasan on 05.11.2015.
 */
public class LoggerException extends Exception {

    public LoggerException(String s) {
        super(s);
        System.out.println("Exception in Logger " + s);
    }
}
