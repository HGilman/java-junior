package com.acme.edu;


import com.acme.edu.exceptions.PrinterException;

/**
 * Created by Khasan on 03.11.2015.
 */
public class IntLoggerState extends LoggerState  {

    public static final int INT = 0;
    public static final int INT_TWODIM_ARRAY = 2;
    public static final int INT_MULTI_ARRAY = 3;


    private int format;

    /**
     * store here sum of consequentially entered int
     * or sum of elements of int array
     */
    private long sum = 0;

    public IntLoggerState(){}

    @Override
    public void setFormat(int format) {
        this.format = format;
    }

    @Override
    public void flush()  {
        try {
            switch (format) {
                case INT:
                    if (!(checkIfOverInteger())) {
                        printer.print("primitive: " + sum + SEP);
                    } else {
                        printer.print(buffer);
                    }
                    sum = 0;
                    break;
                case INT_TWODIM_ARRAY:
                    printer.print(buffer);
                    break;
                case INT_MULTI_ARRAY:
                    printer.print(buffer);
                    break;
                default:
            }
            buffer = "";
        } catch (PrinterException e ) {
            System.out.println();
        }

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
