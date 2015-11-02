package com.acme.edu;



/**
 * This class prints in console  information about
 * what is happening in whole project
 */
public class Logger {

    /**
     * this is massage to console
     */
    private static String text = null;
    private static final String SEP = System.lineSeparator();
    /**
     * stores sum of entered numbers
     */
    private static long sum = 0;
    private static String numberSequence = "";
    private static String typeOfMessage = "";

    private static String typeOfNum = "";
    private static String previousString = "";
    private static int stringCounter = 1;

    public static void log(int num) {
        if(typeOfNum.length() == 0){
            typeOfNum = "int";
        }
        sum = sum + num;
        numberSequence = numberSequence + num + SEP;
    }

    public static void log(byte num) {
        if(typeOfNum.length() == 0){
            typeOfNum = "byte";
        }
        sum = sum + num;
        numberSequence = numberSequence + num + SEP;
    }

    public static void log(char ch) {

    }

    public static void log(boolean bool) {

    }


    public static void log(String string) {
        if (string.equals(previousString)){
            stringCounter++;
        } else {
            if ( !previousString.equals("") && stringCounter == 1){
                typeOfMessage =  typeOfMessage + previousString + SEP;
                previousString = string;

            } else if (stringCounter > 1) {
                typeOfMessage = typeOfMessage + previousString + "x(" + stringCounter + ")" + SEP;
                previousString = string;

            } else {
                previousString = string;
                typeOfMessage = string;
            }
        }
    }

    public static void log(Object obj) {

    }


    /**
     * call this when you end to write numbers or
     *  after last call of Logger.log() method
     */
    public static void close() {


        if (typeOfNum.equals("byte")){
            if (checkIfOverByte()){
                text = typeOfMessage +  SEP + numberSequence;
            } else {
                text = typeOfMessage + SEP + sum + SEP;
            }


        } else if (typeOfNum.equals("int")){
            if (checkIfOverInteger()){
                text = typeOfMessage +  SEP + numberSequence;
            } else {
                text = typeOfMessage +  SEP + sum + SEP;
            }
        } else {
            text = typeOfMessage;
        }

        previousString = "";
        typeOfMessage = "";
        numberSequence = "";
        sum = 0;
        printToConsole(text);
    }


    /**
    * Prints message to console
     */
    private static void printToConsole(String message){
        System.out.print( message );
    }


    private static boolean checkIfOverInteger(){

        if (sum > Integer.MAX_VALUE){
            return   true;
        } else {
            return   false;
        }
    }

    private static boolean checkIfOverByte(){
        if (sum > Byte.MAX_VALUE){
            return true;
        } else {
            return false;
        }
    }


}
