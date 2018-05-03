package util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CalucateTest {
    @Test
    public void testAdd() {
        assertEquals(6, new Calculate().add(3, 3));
    }
    @Test
    public void testSubtract() {
        assertEquals(3, new Calculate().subtract(5, 2));
    }
    @Test
    public void testMultiply() {
        assertEquals(4, new Calculate().multiply(2, 2));
    }
    @Test
    public void testDivide() {
        assertEquals(3, new Calculate().divide(6, 2));
    }
}
