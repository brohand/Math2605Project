import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Vector test class
 *
 * @author Patrick Tam
 * @version 0.1
 */
public class VectorTest {

    @Test
    public void testGet() throws Exception {
        double[] a = {1,2,3,4,5};
        Vector v = new Vector(a);
        for (int i = 0; i < a.length; i++) {
            assertTrue(a[i] == v.get(i));
        }
    }

    @Test
    public void testMinus() throws Exception {
        Vector a = new Vector(1,2,3,4,5);
        Vector b = new Vector(5,4,3,2,1);
        Vector expected = new Vector(-4, -2,0,2,4);
        assertEquals(a.minus(b), expected);
    }

    @Test
    public void testPlus() throws Exception {
        Vector a = new Vector(1,2,3,4,5);
        Vector b = new Vector(5,4,3,2,1);
        Vector expected = new Vector(6,6,6,6,6);
        assertEquals(a.plus(b), expected);
    }

    @Test
    public void testDot() throws Exception {
        Vector a = new Vector(1,2,3,4,5);
        Vector b = new Vector(5,4,3,2,1);
        assertEquals(35, a.dot(b), 0);
    }

    @Test
    public void testNorm() throws Exception {
        Vector a = new Vector(1,2,3,4,5);
        assertEquals(7.416, a.norm(), 0.001);
    }

    @Test
    public void testLength() throws Exception {
        Vector a = new Vector(1,2,3,4,5);
        assertEquals(5, a.length());
    }

    @Test
    public void testTimes() throws Exception {
        Vector a = new Vector(1,2,3,4,5);
        assertEquals(new Vector(2,4,6,8,10), a.times(2));
    }
}