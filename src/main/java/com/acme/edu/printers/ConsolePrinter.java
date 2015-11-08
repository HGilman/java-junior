package com.acme.edu.printers;

import com.acme.edu.exceptions.PrinterException;

import java.io.IOException;

/**
 * This class is reliable for output logging messages to console
 */
public class ConsolePrinter extends Printer{

    /**
     * Prints message to console
     */
    @Override
    public void print(String message) {
        System.out.print(message);
    }
}
