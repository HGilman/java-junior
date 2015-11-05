package com.acme.edu;

import com.acme.edu.exceptions.LoggerException;
import com.acme.edu.exceptions.PrinterException;

/**
 * This class prints in console  information about
 * what is happening in whole project
 */
public class Logger implements Closeable {

    private final static LoggerState INT_STATE = new IntLoggerState();
    private final static LoggerState STRING_STATE = new StringLoggerState();
    private final static LoggerState BOOLEAN_STATE = new BooleanLoggerState();
    private final static LoggerState CHAR_STATE = new CharLoggerState();
    private final static LoggerState OBJECT_STATE = new ObjectLoggerState();

    public static final String SEP = System.lineSeparator();
    private LoggerState state = CHAR_STATE;

    /**
     * Prints in console sum of arguments, if we call
     * this method sequentially. So for this:
     * log.(4);
     * log(5);
     * we will see: "primitives: " + 9;
     */
    public void log(int i) throws LoggerException {
        unleashState(INT_STATE, IntLoggerState.INT);
        state.writeToBuffer(i + "");
    }

    /**
     * Prints in console sum of int varArg
     * like this: "primitives: " + (sum of int in varArg);
     */
    public void log(int... varArgArray) throws LoggerException {
        unleashState(INT_STATE, IntLoggerState.INT);
        for (int i : varArgArray) {
            state.writeToBuffer(i + "");
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
        unleashState(INT_STATE, IntLoggerState.INT_TWODIM_ARRAY);
        state.writeToBuffer(toStringIntTwoDimArray(twoDimArray));
    }

    /**
     * Prints multi dim array similarly as matrix array
     */
    public void log (int [][][][] multiArray) throws LoggerException {
        unleashState(INT_STATE, IntLoggerState.INT_MULTI_ARRAY);

        String message = "{" + SEP;
        for (int i = 0; i < multiArray.length; i++) {
            message += "{" + SEP;
            for (int j = 0; j < multiArray[i].length ; j++) {
                message += toStringIntTwoDimArray(multiArray[i][j]);
            }
            message += "}" + SEP;
        }
        message += "}" + SEP;

        state.writeToBuffer(message);
    }

    /**
     * Prints arguments to console
     * like this: "char: " + "ch1"
     */
    public void log(char ch) throws LoggerException {
        unleashState(CHAR_STATE, -1);
        state.writeToBuffer(ch + "");
    }

    /**
     * Prints arguments to console
     * like this: "primitive: " + "bool";
     */
    public void log(boolean bool) throws LoggerException {
        unleashState(BOOLEAN_STATE, -1);
        state.writeToBuffer(bool + "");
    }

    /**
     * Prints arguments to conole
     * like this: "string: " + "string";
     * but if you call this sequentially with the equals
     * strings than we got this:
     * "string: " + "stringArg" + "x(number of sequential stringArg strings)"
     */
    public void log(String string) throws LoggerException {
        unleashState(STRING_STATE, -1);
        state.writeToBuffer(string);
    }

    /**
     * Prints string arguments to console like this:
     * "string: " + "strArg1"
     * "string: " + "strArg2"
     * ...
     */
    public void log(String... stringArray) throws LoggerException {
        unleashState(STRING_STATE, -1);
        state.writeToBuffer(toStringStringArray(stringArray));
    }

    /**
     * Prints to console like this:
     * "reference: " + "objectArg.toString()"
     */
    public void log(Object object) throws LoggerException{
        unleashState(OBJECT_STATE, -1);
        state.writeToBuffer(object + "");
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
    @Override
    public void close() throws LoggerException {
        try {
            state.close();
        } catch (PrinterException e) {
            throw new LoggerException("problem while writing log message in close() method", e);
        }
    }

    /**
     * I call this in the beginning of any log() method
     * to change state if we need , print out buffer and clear it and
     * than set new state and new format
     */
    private void unleashState(LoggerState argState, int format) throws  LoggerException {
        try {
            if (state != argState) {
                state.flush();
                state = argState;
                state.setFormat(format);
            }
        } catch (PrinterException e) {
            throw new LoggerException("problem while changing state or cleaning buffer \n" +
                    "in unleashState method", e);
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
