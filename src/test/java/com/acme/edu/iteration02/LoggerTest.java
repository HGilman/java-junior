package com.acme.edu.iteration02;

import com.acme.edu.Logger;
import com.acme.edu.SysoutCaptureAndAssertionAbility;
import com.acme.edu.printers.RemotePrinter;
import com.acme.edu.exceptions.LoggerException;
import com.acme.edu.printers.ConsolePrinter;
import com.acme.edu.printers.FilePrinter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class LoggerTest implements SysoutCaptureAndAssertionAbility {

    private static final String SEP = System.lineSeparator();
    private static  Logger logger = null;

    //region given
    @Before
    public void setUpSystemOut() throws IOException {
        resetOut();
        captureSysout();
    }

    @Before
    public void createLogger() {
        logger = new Logger();
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
    public void shouldLogSequentIntegersAsSum() throws IOException, LoggerException {
        //region when
        logger.log("str 1");
        logger.log(1);
        logger.log(2);

        logger.log("str 2");
        logger.log(0);
        logger.close();
        //endregion

        //region then
        assertSysoutEquals(
                "string: str 1" + SEP +
                       "primitive: "+  "3" + SEP +
                        "string: str 2" + SEP +
                        "primitive: " + "0" + SEP
        );
        //endregion
    }

    @Test
    public void shouldLogCorrectlyIntegerOverflowWhenSequentIntegers() throws LoggerException {
        //region when
        logger.log("str 1");
        logger.log(10);
        logger.log(Integer.MAX_VALUE);

        logger.log("str 2");
        logger.log(0);
        logger.close();
        //endregion

        //region then
        assertSysoutEquals(
            "string: str 1" + SEP +
            "10" + SEP +
             Integer.MAX_VALUE + SEP +
            "string: str 2" + SEP +
            "primitive: " + "0" + SEP
        );
        //endregion
    }



    @Test
    public void shouldLogSameSubsequentStringsWithoutRepeat() throws IOException, LoggerException {
        //region when
        logger.log("str 1");
        logger.log("str 2");
        logger.log("str 2");
        logger.log(0);

        logger.log("str 2");
        logger.log("str 3");
        logger.log("str 3");
        logger.log("str 3");
        logger.close();
        //endregion

        //region then
        assertSysoutEquals(
                "string: str 1"+ SEP +
                        "string: str 2 (x2)" + SEP +
                        "primitive: 0" + SEP +
                        "string: str 2" + SEP +
                        "string: str 3 (x3)" + SEP
        );
        //endregion
    }


}