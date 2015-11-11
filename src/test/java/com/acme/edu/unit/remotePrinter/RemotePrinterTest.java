package com.acme.edu.unit.remotePrinter;

import com.acme.edu.Logger;
import com.acme.edu.exceptions.LoggerException;
import com.acme.edu.printers.RemotePrinter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;


/**
 * Created by Khasan on 09.11.2015.
 */
public class RemotePrinterTest  {

    private static final int PORT = 1;
    private static final String ipAddress = "127.0.0.1";
    private static Process serverProcess = null;
    private static final String startCommand = "java -cp D:\\Coding\\jetInfoSystems\\javaTraining\\myrepo\\" +
            "java-junior\\target\\classes com.acme.edu.Server";
    private static Logger logger = null;

    @Before
    public void setUp() {
        try {
             serverProcess = Runtime.getRuntime().exec(startCommand);
        } catch (IOException e) {
            System.out.println("problem while executing server!");
        }
        logger = new Logger(new RemotePrinter(ipAddress, PORT));
    }

    @After
    public void stopServer() {
        serverProcess.destroy();
    }

    @Test
    public void shouldReceiveClientMessagesCausedByRemotePrinter () {
        try {
            logger.log("Remote serve logging");
            logger.log("Remote serve logging");
            logger.log("Remote serve logging");
            logger.log("Remote serve logging");
            logger.log("Remote serve logging");
            logger.close();
        } catch (LoggerException e) {
            e.printStackTrace();
        }
    }
}
