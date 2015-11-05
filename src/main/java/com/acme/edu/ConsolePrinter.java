package com.acme.edu;

/**
 * This class is reliable for output logging messages to console
 */
public class ConsolePrinter implements Printable  {

    /**
     * Prints message to console
     */
    @Override
    public void print(String message){
        System.out.print(message);
    }
}
