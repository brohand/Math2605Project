import Jama.Matrix;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Householder test class
 *
 * @author Patrick Tam
 * @version 0.1
 */
public class HouseholderTestTam {

    @Before
    public void setup() {

    }
    @Test
    public void testGetREasy() throws Exception {
        double[][] a = {
                {0,3,4},
                {3,1,0},
                {4,0,0}
        };
        double[][] r = {
                {-5,-0.6,0},
                {0,-3.104,-3.864},
                {0,0,-1.030}
        };
        Matrix aMatrix = new Matrix(a);
        System.out.println("R calculated");
        Householder.getR(aMatrix).print(3, 3);
        System.out.println("R expected");
        (new Matrix(r)).print(3,3);

    }

    @Test
    public void testGetQEasy() throws Exception {
        double[][] a = {
                {0,3,4},
                {3,1,0},
                {4,0,0}
        };
        double[][] q = {
                {0,-0.966,-0.257},
                {-0.6,-0.206,0.772},
                {-0.8,0.154,-0.579}
        };
        Matrix aMatrix = new Matrix(a);
        System.out.println("Q calculated");
        Householder.getQ(aMatrix).print(3, 3);
        System.out.println("Q expected");
        (new Matrix(q)).print(3,3);
    }
}