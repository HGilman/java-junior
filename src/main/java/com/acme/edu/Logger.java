package com.acme.edu;


import java.io.*;
import java.nio.charset.Charset;

/**
 * This class just prints in console or somewhere else information about
 * what is happening in whole project
 */
public class Logger {

    private static int counter = 0;

    public static void log(int num) {
       // printToConsole("primitive: ", String.valueOf(message));
        counter = counter + num;
    }

    public static void log(char ch) {
        printToConsole("char: ", String.valueOf(ch));
    }

    public static void log(boolean bool) {
        printToConsole("primitive: ", String.valueOf(bool));
    }


    public static void log(String message) {
        printToConsole("primitive: ", String.valueOf(message));
    }

    public static void log(Object obj) {
        printToConsole("reference: ", obj.toString());
    }


    public static void close() {
        System.out.print(counter + "\n");
        counter = 0;
    }


    /**
    * Prints message to console
     */
    private static void printToConsole(String parameter, String message){
        System.out.print(parameter + message + "\n");
    }



    /**
     * Prints message to outPut stream
     * @param outputStream
     * @param parameter
     * @param message
     */
    private static void printToOutputStream(OutputStream outputStream, String parameter, String message) {

        String str = parameter + message;
        try {
            outputStream.write(str.getBytes(Charset.forName("UTF-8")));
        } catch (IOException e){
            e.printStackTrace();
        }
    }


}
