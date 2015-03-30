import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Matrix test class
 *
 * @author Patrick Tam
 * @version 0.1
 */
public class MatrixTest {


    double[][] A2 = {
            {1,2,3,4},
            {5,6,7,8},
            {9,10,11,12},
            {13,14,15,16}
    };
    Matrix A = new Matrix(A2);

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testGet() throws Exception {
        assertEquals(A2[2][3], A.get(2,3), 0);
    }

    @Test
    public void testSet() throws Exception {
        A.set(2,3,4);
        assertEquals(A.get(2,3), 4, 0);
        A.set(2,3,12);
    }

    @Test
    public void testPlus() throws Exception {
        double[][] b = {
                {-1,-2,-3,-4},
                {-5,-6,-7,-8},
                {-9,-10,-11,-12},
                {-13,-14,-15,-16}
        };
        double[][] c = {
                {0,0,0,0},
                {0,0,0,0},
                {0,0,0,0},
                {0,0,0,0}
        };
        Matrix B = new Matrix(b);
        Matrix C = new Matrix(c);
        assertTrue(A.plus(B).equals(C));
    }

    @Test
    public void testMinus() throws Exception {
        double[][] b = {
                {-1,-2,-3,-4},
                {-5,-6,-7,-8},
                {-9,-10,-11,-12},
                {-13,-14,-15,-16}
        };
        double[][] c = {
                {0,0,0,0},
                {0,0,0,0},
                {0,0,0,0},
                {0,0,0,0}
        };
        Matrix B = new Matrix(b);
        Matrix C = new Matrix(c);
        assertTrue(A.minus(B.times(-1)).equals(C));
    }

    @Test
    public void testTimes() throws Exception {

    }

    @Test
    public void testTimes1() throws Exception {

    }

    @Test
    public void testTimes2() throws Exception {

    }

    @Test
    public void testTranspose() throws Exception {

    }

    @Test
    public void testNorm() throws Exception {

    }

    @Test
    public void testForwardsSubstitution() throws Exception {
        double[][] a = {
                {1,2,3,4},
                {0,5,6,7},
                {0,0,8,9},
                {0,0,0,10}
        };
        Matrix A = new Matrix(a);
        Vector b = new Vector(2*65,2*118,2*115,2*70);
        Vector x = new Vector(11,12,13,14);
        assertTrue(A.backwardsSubstitution(b).equals(x));
    }

    @Test
    public void testBackwardsSubstitution() throws Exception {
        double[][] a = {
                {1,0,0,0},
                {2,3,0,0},
                {4,5,6,0},
                {7,8,9,10}
        };
        Matrix A = new Matrix(a);
        Vector b = new Vector(11,58,182,430);
        Vector x = new Vector(11,12,13,14);
        assertTrue(A.forwardsSubstitution(b).equals(x));
    }

    @Test
    public void testGetRowDimension() throws Exception {

    }

    @Test
    public void testGetColumnDimension() throws Exception {

    }

    @Test
    public void testGetArray() throws Exception {

    }

    @Test
    public void testGetArrayCopy() throws Exception {

    }
}