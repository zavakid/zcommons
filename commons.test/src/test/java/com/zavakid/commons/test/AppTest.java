package com.zavakid.commons.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
    public void testScala() {
        TestScala scala = new TestScala();
        scala.setHello(5);
        assertEquals(5, scala.getHello());
        scala.sayHello();
    }
}
