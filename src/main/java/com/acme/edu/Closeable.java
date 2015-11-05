package com.acme.edu;

import com.acme.edu.exceptions.PrinterException;

/**
 * Created by Khasan on 03.11.2015.
 */
public interface Closeable {
    void close() throws PrinterException;
}
