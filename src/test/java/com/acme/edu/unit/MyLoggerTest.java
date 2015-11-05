package com.acme.edu.unit;

import com.acme.edu.Logger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito.*;


/**
 * Created by Khasan on 05.11.2015.
 */

public class MyLoggerTest {

    private Logger logger;
    private Object dummy;

    @Before
    public void setUp() {
        logger = new Logger();
        dummy = new Object();
    }

    @Test
    public void shouldLogMethodCountSameStrings() {

    }

}
