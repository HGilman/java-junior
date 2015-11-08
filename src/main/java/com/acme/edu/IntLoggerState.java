package com.acme.edu;


import com.acme.edu.exceptions.PrinterException;
import com.acme.edu.printers.Printer;

import java.io.IOException;

/**
 * Created by Khasan on 03.11.2015.
 */
public class IntLoggerState extends LoggerState  {

    public static final int INT = 0;
    public static final int INT_TWODIM_ARRAY = 1;
    public static final int INT_MULTI_ARRAY = 2;


    /**
     * store here sum of consequentially entered int
     * or sum of elements of int array
     */
    private long sum = 0;

    public IntLoggerState(){}


    @Override
    public void flush() throws IOException {
        switch (format) {
            case INT:
                if (!(checkIfOverInteger())) {
                    printAllPrinters("primitive: " + sum + SEP);
                } else {
                    printAllPrinters(buffer);
                }
                sum = 0;
                break;
            case INT_TWODIM_ARRAY:
                printAllPrinters(buffer);
                break;
            case INT_MULTI_ARRAY:
               printAllPrinters(buffer);
                break;
            default:
        }
        buffer = "";
    }

    /**
     * @param string is actually int, vararg of int
     * or two dim int array
     */
    @Override
    public void writeToBuffer(String string) {

        switch (format) {
            case INT:
                int i = Integer.parseInt(string);
                sum += i;
                buffer += i + SEP;
                break;
            case INT_TWODIM_ARRAY:
                buffer += "primitives matrix: " + string;
                break;
            case INT_MULTI_ARRAY:
                buffer += "primitives multimatrix: " + string;
                break;
            default:
        }

    }

    private boolean checkIfOverInteger(){
        return (sum > Integer.MAX_VALUE) ? true : false;
    }



}
