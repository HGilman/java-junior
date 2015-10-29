package com.acme.edu;

public class Logger {





    public static void log(int message) {
        printOut("primitive: " + message);
    }

    public static void log(byte message) {
        printOut("primitive: " + message);
    }

    public static void log(char ch) {
        printOut("char: " + ch);
    }

    public static void log(boolean bool) {
        printOut("primitive: " + bool);
    }

    private static void printOut(String message){
        System.out.println(message);
    }
}
