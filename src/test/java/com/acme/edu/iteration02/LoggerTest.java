package com.acme.edu.iteration02;

import com.acme.edu.Logger;
import com.acme.edu.SysoutCaptureAndAssertionAbility;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class LoggerTest implements SysoutCaptureAndAssertionAbility {

    private static final String SEP = System.lineSeparator();

    //region given
    @Before
    public void setUpSystemOut() throws IOException {
        resetOut();
        captureSysout();
    }
    //endregion


    @After
    public void tearDown() {
        resetOut();
    }

    @Test
    public void shouldLogSequentIntegersAsSum() throws IOException {
        //region when
        Logger.log("str 1");
        Logger.log(1);
        Logger.log(2);
        Logger.close();

        Logger.log("str 2");
        Logger.log(0);
        Logger.close();
        //endregion

        //region then
        assertSysoutEquals(
                "str 1" + SEP +
                        "3" + SEP +
                        "str 2" + SEP +
                        "0" + SEP
        );
        //endregion
    }

    @Test
    public void shouldLogCorrectlyIntegerOverflowWhenSequentIntegers() {
        //region when
        Logger.log("str 1");
        Logger.log(10);
        Logger.log(Integer.MAX_VALUE);
        Logger.close();

        Logger.log("str 2");
        Logger.log(0);
        Logger.close();
        //endregion

        //region then
        assertSysoutEquals(
            "str 1" + SEP +
            "10" + SEP +
             Integer.MAX_VALUE + SEP +
            "str 2" + SEP +
            "0" + SEP
        );
        //endregion
    }

    @Test
    public void shouldLogCorrectlyByteOverflowWhenSequentBytes() {
        //region when
        Logger.log("str 1");
        Logger.log((byte)10);
        Logger.log((byte)Byte.MAX_VALUE);
        Logger.close();

        Logger.log("str 2");
        Logger.log(0);
        Logger.close();
        //endregion

        //region then
        assertSysoutEquals(
                "str 1" + SEP +
                        "10" + SEP +
                        Byte.MAX_VALUE + SEP +
                        "str 2"+ SEP +
                        "0" + SEP
        );
        //endregion
    }

//    @Test
//    public void shouldLogSameSubsequentStringsWithoutRepeat() throws IOException {
//        //region when
//        Logger.log("str 1");
//        Logger.log("str 2");
//        Logger.log("str 2");
//        Logger.log(0);
//        Logger.close();
//
//        Logger.log("str 2");
//        Logger.log("str 3");
//        Logger.log("str 3");
//        Logger.log("str 3");
//        Logger.close();
//        //endregion
//
//        //region then
//        assertSysoutEquals(
//                "str 1"+ SEP +
//                        "str 2 (x2)" + SEP +
//                        "0" + SEP +
//                        "str 2" + SEP +
//                        "str 3 (x3)" + SEP
//        );
//        //endregion
//    }


}