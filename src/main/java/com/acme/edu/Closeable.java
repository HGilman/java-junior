package com.acme.edu;

import com.acme.edu.exceptions.LoggerException;
import com.acme.edu.exceptions.PrinterException;

import java.io.IOException;


public interface Closeable {
    void close() throws IOException, LoggerException;
}
