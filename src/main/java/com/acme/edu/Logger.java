package com.acme.edu;



/**
 * This class prints in console  information about
 * what is happening in whole project
 */
public class Logger {

    private enum Type{
        INT, CHAR, BOOL, STRING, OBJ, ARRAY, TwoDimArray
    }

    /**
     * this is massage to console
     */
    private static String buffer = "";
    private static Type typeBuffer;
    private static final String SEP = System.lineSeparator();
    /**
     * stores sum of entered numbers
     */
    private static long sum = 0;
    private static int stringCounter = 1;

    public static void log(int num) {
        unleashBuffer(Type.INT);
        sum = sum + num;
        buffer = buffer + num + SEP;
    }

    public static void log(int[] array){
        String message = printArray(array);
        printToConsole(message, Type.ARRAY);
    }

    public static void log(int[][] twoDimArray){

        int length = twoDimArray.length;
        String message = "{" + SEP;
        for (int i = 0; i < length; i++) {
            message += printArray(twoDimArray[i]);
        }
        message += "}" + SEP;
        printToConsole(message, Type.TwoDimArray);
    }


    public static void log(char ch) {
        unleashBuffer(Type.CHAR);
        printToConsole("char: " + ch  + SEP, typeBuffer);
    }

    public static void log(boolean bool) {
        unleashBuffer(Type.BOOL);
        printToConsole("primitive: " + bool  + SEP, typeBuffer);
    }


    public static void log(String string) {

        unleashBuffer(Type.STRING);

        if(buffer.equals("")){
            buffer = string;
        } else if (string.equals(buffer)){
            stringCounter++;
        } else {
            printToConsole(buffer, typeBuffer);
            stringCounter = 1;
            buffer = string;
            typeBuffer = Type.STRING;
        }
    }

    public static void log(Object obj) {

        unleashBuffer(Type.OBJ);
        printToConsole("reference: " + obj + SEP, typeBuffer);
    }


    /**
     * you  must call this after last call of Logger.log() method
     */
    public static void close() {
        printToConsole(buffer, typeBuffer);
        resetFields();
    }


    /**
    * Prints message to console
     */
    private static void printToConsole(String message, Type typeBuffer){

       switch (typeBuffer){
           case STRING:
               if (stringCounter == 1) {
                   System.out.print("string: " + message + SEP);
               } else {
                   System.out.print("string: " + message + " (x" + stringCounter + ")" + SEP);
               }
               break;
           case INT:
               if (checkIfOverInteger()){
                   System.out.print(message);
               } else {
                   System.out.print("primitive: " + sum + SEP);
               }
               break;
           case CHAR:
               System.out.print(message);
               break;
           case BOOL:
               System.out.print(message);
               break;
           case OBJ:
               System.out.print(message);
               break;
           case ARRAY:
               System.out.print("primitives array: " + message);
               break;
           case TwoDimArray:
               System.out.print("primitives matrix: " + message);
       }
    }


    /**
     * we call this method at the top of any log() method to
     * delete content of buffer if there is something and reset fields
     *
     * when typeBuffer == type we actually count sum of integers or counts of string, so
     * we must skip cleaning buffer and resetting our fields.
     * @param type is the same as in log (type) method , which called this method
     */
    private static void unleashBuffer(Type type){
        if (!(typeBuffer == type)  && !buffer.equals("")){
            printToConsole(buffer, typeBuffer);
            resetFields();
        }
        typeBuffer = type;
    }

    /**
     * checks if sum of integers more then Integer.MAX_VALUE
     */
    private static boolean checkIfOverInteger(){

        if (sum > Integer.MAX_VALUE){
            return true;
        } else {
            return false;
        }
    }

    private static void resetFields(){
        typeBuffer = null;
        buffer = "";
        sum = 0;
        stringCounter = 1;
    }

    private static String printArray (int[] array){
        String message = "{";
        for (int i = 0; i < array.length; i++){
            if (i != array.length - 1) {
                message += array[i] + ", ";
            } else {
                message += array[i] + "}" + SEP;
            }
        }
        return message;
    }


}
