import Jama.Matrix;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Power method test class
 *
 * @author Patrick Tam
 * @version 0.2
 */
public class PowerMethodTestTam {

    @Test
    public void testGetEigenvector() throws Exception {
        double[][] aM = {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };
        Matrix a = new Matrix(aM);
        double[] u0m = {1,1,1};
        Vector u0 = new Vector(u0m);

        System.out.println("Eigenvector: " + PowerMethod.getEigenvector(a, 0.000000001, u0));
    }
}