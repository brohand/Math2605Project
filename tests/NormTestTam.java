import Jama.Matrix;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Norm test class
 *
 * @author Patrick Tam
 * @version 0.1
 */
public class NormTestTam {

    @Test
    public void testGetNorm() throws Exception {
        double[][] a = {
                {1, 0.5, 0.333333, 0.25},
                {0.5, 0.333333, 0.25, 0.2},
                {0.333333, 0.25, 0.2, 0.166667},
                {0.25, 0.2, 0.166667, 0.142857}
        };
        Matrix hilbert = new Matrix(a);
        System.out.println("Norm: " + Norm.getNorm(hilbert));
    }
}