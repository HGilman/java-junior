package com.acme.edu;


import java.io.*;
import java.nio.charset.Charset;

/**
 * This class just prints in console or somewhere else information about
 * what is happening in whole project
 */
public class Logger {


    public static void log(int message) {

        printToConsole("primitive: ", String.valueOf(message));
    }

    public static void log(byte message) {
        printToConsole("primitive: ", String.valueOf(message));
    }

    public static void log(char ch) {
        printToConsole("char: ", String.valueOf(ch));
    }

    public static void log(boolean bool) {
        printToConsole("primitive: ", String.valueOf(bool));
    }

    /**
    * Prints message to console
     */
    private static void printToConsole(String parameter, String message){
        System.out.println(parameter + message);
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
