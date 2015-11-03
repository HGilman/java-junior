package com.acme.edu;



/**
 * Created by Khasan on 03.11.2015.
 */
public class IntLoggerState extends LoggerState  {

    public static final int INT = 0;
    public static final int INT_VARARG = 1;
    public static final int INT_TWODIM_ARRAY = 2;


    private int format;
    private int sum = 0;

    public IntLoggerState(){}

    @Override
    public void setFormat(int format){
        this.format = format;
    }

    @Override
    public void flush() {
        if (!(checkIfOverInteger())){
            printer.print("primitive: " + sum + SEP);
        } else {
            printer.print(buffer);
        }
        buffer = "";
        sum = 0;
    }

    /**
     * We know here that string is int
     * @param string
     */
    @Override
    public void writeToBuffer(String string) {

        int i = Integer.parseInt(string);
        if (!checkIfOverInteger()){
            sum += i;
        }
        buffer += i + SEP;
    }


    /**
     * checks if sum of integers more then Integer.MAX_VALUE
     */
    private boolean checkIfOverInteger(){
        return (sum > Integer.MAX_VALUE) ? true : false;
    }


}
