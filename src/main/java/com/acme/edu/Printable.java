package com.acme.edu;

import com.acme.edu.exceptions.PrinterException;

/**
 * You should implement this making custom realization of
 * class (which may be called Printer) , which is reliable for
 * output messages of Logger
 */
public interface Printable {
    void print(String message) throws PrinterException;
}
