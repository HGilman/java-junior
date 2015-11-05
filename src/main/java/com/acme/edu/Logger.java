package com.acme.edu;

import com.acme.edu.exceptions.LoggerException;
import com.acme.edu.exceptions.PrinterException;

/**
 * This class prints in console  information about
 * what is happening in whole project
 */
public class Logger implements Closeable {


//    public static final String LOG_PRIMITIVE = "primitive: ";
//    public static final String LOG_CHAR = "char: ";
//    public static final String LOG_STRING = "string: ";
//    public static final String LOG_REFERENCE = "reference: ";
//    public static final String LOG_PRIMITIVES_MATRIX = "primitives matrix: ";
//    public static final String LOG_PRIMITIVES_MULTIMATRIX = "primitives multimatrix: ";


    private final static LoggerState INT_STATE = new IntLoggerState();
    private final static LoggerState STRING_STATE = new StringLoggerState();
    private final static LoggerState BOOLEAN_STATE = new BooleanLoggerState();
    private final static LoggerState CHAR_STATE = new CharLoggerState();
    private final static LoggerState OBJECT_STATE = new ObjectLoggerState();




    public static final String SEP = System.lineSeparator();

    private LoggerState state = CHAR_STATE;

    public void log(int i) {

        unleashState(INT_STATE, IntLoggerState.INT);
        state.writeToBuffer(i + "");

    }

    public void log(int... varArgArray) {
        unleashState(INT_STATE, IntLoggerState.INT);
        for (int i : varArgArray) {
            state.writeToBuffer(i + "");
        }
    }

    public void log(int[][] twoDimArray) {
        unleashState(INT_STATE, IntLoggerState.INT_TWODIM_ARRAY);
        int length = twoDimArray.length;
        String message = "{" + SEP;
        for (int i = 0; i < length; i++) {
            message += printIntArray(twoDimArray[i]);
        }
        message += "}" + SEP;
        state.writeToBuffer(message);
    }

    public void log (int [][][][] multiArray) {
        unleashState(INT_STATE, IntLoggerState.INT_MULTI_ARRAY);
        int firstLength = multiArray.length;
        String message = "{" + SEP;
        for (int i = 0; i < firstLength; i++) {

        }
        message += "}" + SEP;
        state.writeToBuffer(message);
    }
    public void log(char ch) {
        unleashState(CHAR_STATE, -1);
        state.writeToBuffer(ch + "");
    }
    public void log(boolean bool) {
        unleashState(BOOLEAN_STATE, -1);
        state.writeToBuffer(bool + "");
    }

    public void log(String string) {
        unleashState(STRING_STATE, -1);
        state.writeToBuffer(string);
    }

    public void log(String... stringArray) {
        unleashState(STRING_STATE, -1);
        state.writeToBuffer(printStringArray(stringArray));
    }

    public void log(Object object) {
        unleashState(OBJECT_STATE, -1);
        state.writeToBuffer(object + "");
    }

    private void unleashState(LoggerState argState, int format) {
        try {
            if (state != argState) {
                state.flush();
                state = argState;
                state.setFormat(format);
            } else {
            }
        } catch (PrinterException e) {
            System.out.println("Exception while changing state" + e.getCause());
        }
    }


    // should rename this method
    private  String printIntArray (int[] array) {
        String message = "{";
        for (int i = 0; i < array.length; i++) {
            if (i != array.length - 1) {
                message += array[i] + ", ";
            } else {
                message += array[i] + "}" + SEP;
            }
        }
        return message;
    }

    private  String printStringArray (String[] array) {
        String message = "";
        for (String s : array) {
            message += s +  SEP;
        }
        return message;
    }

    public void close(){

        try {
        state.close();
        } catch (PrinterException e) {
            System.out.println("Exception while closing " + e.getCause());
        }
    }
}
