import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Matrix Multiplication test class
 *
 * @author Patrick Tam
 * @version 0.1
 */
public class MultiplyTestTam {

    @Before
    public void setup() {

    }
    @Test
    public void testMultiply() throws Exception {
        double[][] a = {
                {1,2,3,4,5},
                {6,7,8,9,10},
                {11,12,13,14,15},
                {16,17,18,19,20}
        };
        double[][] b = {
                {1,2},
                {3,4},
                {5,6},
                {7,8},
                {9,10},
        };
        double[][] c = {
                {95,110},
                {220,260},
                {345,410},
                {470,560}
        };
        Matrix d = Multiply.multiply(new Matrix(a), new Matrix(b));
        System.out.println("Multiplied Matrix Actual: ");
        d.print(4, 2);
        System.out.println("Multiplied Matrix Expected: ");
        (new Matrix(c)).print(4, 2);
        assertArrayEquals(c,d.getArray());
    }
    @Test(expected=MatrixException.class)
    public void testNullExcpetion() throws Exception {
        double[][] c = {
                {95,110},
                {220,260},
                {345,410},
                {470,560}
        };
        Multiply.multiply(null, new Matrix(c));
    }

//    @Test(expected=MatrixException.class)
//    public void testEmptyException() throws Exception {
//        double[][] stuff = new double[0][0];
//        Multiply.multiply(new Matrix(stuff), new Matrix(stuff));
//    }

    @Test(expected=MatrixException.class)
    public void testWrongSizeException() throws Exception {
        double[][] a= new double[1][2];
        double[][] b = new double[3][4];
        Multiply.multiply(new Matrix(a),new Matrix(b));
    }
}