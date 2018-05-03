package util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ErrorAndFailureTest {

    @Test
    public void testAdd() {
        assertEquals(5, new Calculate().add(3, 3));
    }

    @Test(expected = ArithmeticException.class)
    public void testDivide() {
        assertEquals(3, new Calculate().divide(6, 0));
    }

    @Test(timeout = 3000)
    public void testReadFile() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
