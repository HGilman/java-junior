package com.acme.edu.printers;

import com.acme.edu.exceptions.PrinterException;

import java.io.IOException;

/**
 * You should implement this making custom realization of
 * class, which is reliable for
 * output messages of Logger
 */
public abstract class Printer {

    public abstract void print(String message) throws  IOException;

}
