import Jama.Matrix;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Power method test class
 *
 * @author Patrick Tam
 * @version 0.1
 */
public class PowerMethodTestTam {

    @Test
    public void testGetEigenvector() throws Exception {
        double[][] aM = {
                {2,-12},
                {1,-5}
        };
        Matrix a = new Matrix(aM);
        double[] u0m = {1,1};
        Vector u0 = new Vector(u0m);

        System.out.println("Eigenvector: " + PowerMethod.getEigenvector(a, 0.000000001, u0));
    }
}