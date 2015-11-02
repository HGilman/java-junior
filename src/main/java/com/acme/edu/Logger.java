package com.acme.edu;



/**
 * This class prints in console  information about
 * what is happening in whole project
 */
public class Logger {

    /**
     * this is massage to console
     */
    private static String buffer = "";
    private static String typeBuffer = "";
    private static final String SEP = System.lineSeparator();
    /**
     * stores sum of entered numbers
     */
    private static long sum = 0;
    private static int stringCounter = 1;

    public static void log(int num) {
        unleashBuffer("int");
        sum = sum + num;
        buffer = buffer + num + SEP;
    }


    public static void log(char ch) {
        unleashBuffer("char");
        printToConsole("char: " + ch  + SEP, typeBuffer);
    }

    public static void log(boolean bool) {
        unleashBuffer("boolean");
        printToConsole("primitive: " + bool  + SEP, typeBuffer);
    }


    public static void log(String string) {

        unleashBuffer("string");

        if(buffer.equals("")){
            buffer = string;
        } else if (string.equals(buffer)){
            stringCounter++;
        } else {
            printToConsole(buffer, typeBuffer);
            stringCounter = 1;
            buffer = string;
            typeBuffer = "string";
        }
    }

    public static void log(Object obj) {

        unleashBuffer("object");
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
    private static void printToConsole(String message, String typeBuffer){

       switch (typeBuffer){
           case "string":
               if (stringCounter == 1) {
                   System.out.print("string: " + message + SEP);
               } else {
                   System.out.print("string: " + message + " (x" + stringCounter + ")" + SEP);
               }
               break;
           case "int":
               if (checkIfOverInteger()){
                   System.out.print(message);
               } else {
                   System.out.print("primitive: " + sum + SEP);
               }
               break;
           case "char":
               System.out.print(message);
               break;
           case "boolean":
               System.out.print(message);
               break;
           case "object":
               System.out.print(message);
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
    private static void unleashBuffer(String type){
        if (!typeBuffer.equals(type) && !typeBuffer.equals("") && !buffer.equals("")){
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
            return   true;
        } else {
            return   false;
        }
    }

    private static void resetFields(){
        typeBuffer = "";
        buffer = "";
        sum = 0;
        stringCounter = 1;
    }


}
