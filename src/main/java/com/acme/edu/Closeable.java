package com.acme.edu;

import com.acme.edu.exceptions.PrinterException;


public interface Closeable {
    void close() throws PrinterException;
}
