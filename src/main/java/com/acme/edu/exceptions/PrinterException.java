package com.acme.edu.exceptions;

/**
 * Created by Khasan on 05.11.2015.
 */
public class PrinterException extends Exception {

    public PrinterException(String s) {
        super(s);
        System.out.println("Exception while printing " + s);
    }


}
