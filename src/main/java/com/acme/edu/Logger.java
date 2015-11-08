package com.acme.edu;

import com.acme.edu.exceptions.LoggerException;
import com.acme.edu.printers.Printer;
import com.acme.edu.states.*;

import java.io.IOException;

/**
 * This class prints in console  information about
 * what is happening in whole project
 */
public class Logger {

    private final static String BOOLEAN_DECOR = "primitive: ";
    private final static String CHAR_DECOR = "char: ";
    private final static String OBJECT_DECOR = "reference: ";
    public final static String INT_DECOR = "primitive: ";
    public final static String TWODIM_INT_DECOR = "primitives matrix: ";
    public final static String MULTIDIM_INT_DECOR = "primitives multimatrix: ";
    private final static String STRING_DECOR = "string: ";


    public static final String SEP = System.lineSeparator();
    private LoggerState state = null;

    public Logger (Printer... varArgPrinter) {
        LoggerState.setUpPrinters(varArgPrinter);
        state = new OtherState(BOOLEAN_DECOR, "");
    }

    /**
     * Prints in console sum of arguments, if we call
     * this method sequentially. So for this:
     * log.(4);
     * log(5);
     * we will see: "primitives: " + 9;
     */
    public void log(int i) throws LoggerException {
        try {
            state = state.switchToNewState(new IntLoggerState(INT_DECOR, ""));
            state.writeToBuffer(i + "");
        } catch (IOException e) {
            throw new LoggerException("problem in log(int i)" , e);
        }
    }

    /**
     * Prints in console sum of int varArg
     * like this: "primitives: " + (sum of int in varArg);
     */
    public void log(int... varArgArray) throws LoggerException {
        try {
            state = state.switchToNewState(new IntLoggerState(INT_DECOR, ""));
            for (int i : varArgArray) {
                state.writeToBuffer(i + "");
            }
        } catch (IOException e) {
            throw new LoggerException("problem in log(int... varArg)", e);
        }
    }

    /**
     * Prints two dimensional array to console in this format:
     *  "primitives matrix: {
     *  {..}
     *  {...}
     *  ...
     *  }"
     */
    public void log(int[][] twoDimArray) throws LoggerException {
        try {
            state = state.switchToNewState(new IntLoggerState(TWODIM_INT_DECOR, ""));
            state.writeToBuffer(toStringIntTwoDimArray(twoDimArray));
        } catch (IOException e) {
          throw new LoggerException("problem in log(int[][]) method", e);
        }
    }

    /**
     * Prints multi dim array similarly as matrix array
     */
    public void log (int [][][][] multiArray) throws LoggerException {
        try {
            state = state.switchToNewState(new IntLoggerState(MULTIDIM_INT_DECOR, ""));
            String message = "{" + SEP;
            for (int i = 0; i < multiArray.length; i++) {
                message += "{" + SEP;
                for (int j = 0; j < multiArray[i].length; j++) {
                    message += toStringIntTwoDimArray(multiArray[i][j]);
                }
                message += "}" + SEP;
            }
            message += "}" + SEP;

            state.writeToBuffer(message);
        } catch (IOException e) {
            throw new LoggerException("problem in log(multiArray)", e);
        }
    }

    /**
     * Prints arguments to console
     * like this: "char: " + "ch1"
     */
    public void log(char ch) throws LoggerException {
        try {
            state = state.switchToNewState(new OtherState(CHAR_DECOR, ""));
            state.writeToBuffer(ch + "");
        } catch (IOException e) {
            throw new LoggerException("problem in log(char)", e);
        }
    }

    /**
     * Prints arguments to console
     * like this: "primitive: " + "bool";
     */
    public void log(boolean bool) throws LoggerException {
        try {
            state = state.switchToNewState(new OtherState(BOOLEAN_DECOR, ""));
            state.writeToBuffer(bool + "");
        } catch (IOException e) {
            throw new LoggerException("problem in log(bool)", e);
        }
    }

    /**
     * Prints to console like this:
     * "reference: " + "objectArg.toString()"
     */
    public void log(Object object) throws LoggerException{
        try {
            state = state.switchToNewState(new OtherState(OBJECT_DECOR, ""));
            state.writeToBuffer(object + "");
        } catch (IOException e) {
            throw new LoggerException("problem in log(object)", e);
        }
    }

    /**
     * Prints arguments to console
     * like this: "string: " + "string";
     * but if you call this sequentially with the equals
     * strings than we got this:
     * "string: " + "stringArg" + "x(number of sequential stringArg strings)"
     */
    public void log(String string) throws LoggerException {
        try {
            state = state.switchToNewState(new StringLoggerState(STRING_DECOR, ""));
            state.writeToBuffer(string);
        } catch (IOException e) {
            throw new LoggerException("problem in log(string)", e);
        }
    }

    /**
     * Prints string arguments to console like this:
     * "string: " + "strArg1"
     * "string: " + "strArg2"
     * ...
     */
    public void log(String... stringArray) throws LoggerException {
        try {
            state = state.switchToNewState(new StringLoggerState(STRING_DECOR, ""));
            state.writeToBuffer(toStringStringArray(stringArray));
        } catch (IOException e) {
          throw new LoggerException("problem log(String...)", e);
        }
    }

    /**
     * you must always call this method after you finished logging,
     * that is after last call of logger.log(SomeType type) method
     * f.e that is how you usually work with this class:
     * Logger logger = new Logger();
     * logger.log(5);
     * logger.log(6);
     * logger.log("str1");
     * ......
     * logger.close(); -> that is where close method stay
     */
    public void close() throws LoggerException {
        try {
            state.close();
        } catch (IOException e) {
            throw new LoggerException("problem while writing log message in close() method", e);
        }
    }

    /**
     * Makes string from int array in format:
     * {
     * {...}
     * {....}
     * ....
     * }
     */
    private  String toStringIntArray (int[] array) {
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

    private String toStringIntTwoDimArray(int[][] twoDimArray){
        String message = "{" + SEP;
        for (int i = 0; i < twoDimArray.length; i++) {
            message += toStringIntArray(twoDimArray[i]);
        }
        message += "}" + SEP;
        return message;
    }

    private  String toStringStringArray (String[] array) {
        String message = "";
        for (String s : array) {
            message += s +  SEP;
        }
        return message;
    }

}
