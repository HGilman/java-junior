package com.acme.edu.iteration01;

import com.acme.edu.Logger;
import com.acme.edu.SysoutCaptureAndAssertionAbility;
import com.acme.edu.printers.RemotePrinter;
import com.acme.edu.exceptions.LoggerException;
import com.acme.edu.printers.ConsolePrinter;
import com.acme.edu.printers.FilePrinter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

public class LoggerTest implements SysoutCaptureAndAssertionAbility {

    private static final String SEP = System.lineSeparator();
    private static Logger logger = null;

    //region given
    @Before
    public void setUpSystemOut() throws IOException {
        resetOut();
        captureSysout();
    }

    @Before
    public void setLogger() {
        logger = new Logger(new ConsolePrinter(),
                            new FilePrinter("log.txt", "UTF-8"),
                            new RemotePrinter("serverLog.txt", "UTF-8","127.0.0.1", 1));
    }
    //endregion

    @After
    public void tearDown() {
        resetOut();
    }

    @Test
    public void shouldLogInteger() throws IOException {
        //region when
        try {
            logger.log(1);
            logger.log(0);
            logger.log(-1);
            logger.close();
        } catch (LoggerException e) {
            e.printStackTrace();
        }
        //endregion

        //region then
        assertSysoutEquals("primitive: " + 0 + SEP);
        //endregion
    }

    @Test
    public void shouldLogByte() throws IOException {
        //region when
        try {
            logger.log((byte) 1);
            logger.log((byte) 0);
            logger.log((byte) -1);
            logger.close();
        } catch (LoggerException e) {
            e.printStackTrace();
        }
        //endregion

        //region then
        assertSysoutEquals("primitive: " + 0 + SEP);
        //endregion
    }


    @Test
    public void shouldLogChar() throws IOException {
        //region when
        try {
            logger.log('a');
            logger.log('b');
            logger.close();
        } catch (LoggerException e) {
            e.printStackTrace();
        }
        //endregion

        //region then
        assertSysoutContains("char: ");
        assertSysoutContains("a");
        assertSysoutContains("b");
        //endregion
    }

    @Test
    public void shouldLogString() throws IOException {
        //region when
        try {
            logger.log("test string 1");
            logger.log("other str");
            logger.close();
        } catch (LoggerException e) {
            e.printStackTrace();
        }
        //endregion

        //region then
        assertSysoutContains("string: ");
        assertSysoutContains("test string 1");
        assertSysoutContains("other str");
        //endregion
    }

    @Test
    public void shouldLogBoolean() throws IOException {
        //region when
        try {
            logger.log(true);
            logger.log(false);
            logger.close();
        } catch (LoggerException e) {
            e.printStackTrace();
        }
        //endregion

        //region then
        assertSysoutContains("primitive: ");
        assertSysoutContains("true");
        assertSysoutContains("false");
        //endregion
    }

    @Test
    public void shouldLogReference() throws IOException {
        //region when
        try {
            logger.log(new Object());
            logger.close();
        } catch (LoggerException e) {
            e.printStackTrace();
        }
        //endregion

        //region then
        assertSysoutContains("reference: ");
        assertSysoutContains("@");
        //endregion
    }

}