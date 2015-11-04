package com.acme.edu;

/**
 * This class prints in console  information about
 * what is happening in whole project
 */
public class Logger implements Closeable{


    private final static LoggerState INT_STATE = new IntLoggerState();
    private final static LoggerState STRING_STATE = new StringLoggerState();
    private final static LoggerState BOOLEAN_STATE = new BooleanLoggerState();
    private final static LoggerState CHAR_STATE = new CharLoggerState();
    private final static LoggerState OBJECT_STATE = new ObjectLoggerState();

    public static final String SEP = System.lineSeparator();

    private LoggerState state = CHAR_STATE;

    public void log(int i){
        unleashState(INT_STATE, IntLoggerState.INT);
        state.writeToBuffer(i + "");
    }

    public void log(int... varArgArray){
        unleashState(INT_STATE, IntLoggerState.INT_VARARG);
        for (int i : varArgArray){
            state.writeToBuffer(i + "");
        }
    }

    public void log(int[][] twoDimArray){
        unleashState(INT_STATE, IntLoggerState.INT_TWODIM_ARRAY);
        int length = twoDimArray.length;
        String message = "";
        for (int i = 0; i < length; i++) {
            message += printArray(twoDimArray[i]);
        }
        state.writeToBuffer(message);
    }
    public void log(char ch){
        unleashState(CHAR_STATE, -1);
        state.writeToBuffer(ch + "");
    }
    public void log(boolean bool){
        unleashState(BOOLEAN_STATE, -1);
        state.writeToBuffer(bool + "");
    }

    public void log(String string){
        unleashState(STRING_STATE, -1);
        state.writeToBuffer(string);
    }

    public void log(String... string){}

    public void log(Object object){
        unleashState(OBJECT_STATE, -1);
        state.writeToBuffer(object + "");
    }

    private void unleashState(LoggerState argState, int format){
        if (state != argState){
            state.flush();
            state = argState;
            state.setFormat(format);
        } else {}
    }


    private  String printArray (int[] array){
        String message = "{";
        for (int i : array ){
            if (i != array.length - 1) {
                message += array[i] + ", ";
            } else {
                message += array[i] + "}" + SEP;
            }
        }
        return message;
    }

    public void close(){
        state.close();
    }
}
