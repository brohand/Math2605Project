import Jama.Matrix;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Householder test class
 *
 * @author Patrick Tam
 * @version 0.2
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
        System.out.println("Easy R calculated");
        Householder.getR(aMatrix).print(3, 3);
        System.out.println("Easy R expected");
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
        System.out.println("Easy Q calculated");
        Householder.getQ(aMatrix).print(3, 3);
        System.out.println("Easy Q expected");
        (new Matrix(q)).print(3,3);
    }
    @Test
    public void testGetRHard() throws Exception {
        double[][] a = {
                {1, 0.5, 0.333333, 0.25},
                {0.5, 0.333333, 0.25, 0.2},
                {0.333333, 0.25, 0.2, 0.166667},
                {0.25, 0.2, 0.166667, 0.142857}
        };

        double[][] r = {
                {1.19315, 0.670493, 0.474933, 0.369835},
                {0, 0.118533, 0.125655, 0.117542},
                {0, 0, 0.00622177, 0.00956609},
                {0, 0, 0, -0.000187905}
        };
        Matrix aMatrix = new Matrix(a);
        System.out.println("Hard R calculated");
        Householder.getR(aMatrix).print(4, 4);
        System.out.println("Hard R expected");
        (new Matrix(r)).print(4,4);

    }

    @Test
    public void testGetQHard() throws Exception {
        double[][] a = {
                {1, 0.5, 0.333333, 0.25},
                {0.5, 0.333333, 0.25, 0.2},
                {0.333333, 0.25, 0.2, 0.166667},
                {0.25, 0.2, 0.166667, 0.142857}
        };
        double[][] q = {
                {0.838116, -0.522648, 0.153973, 0.0263067},
                {0.419058, 0.441713, -0.727754, -0.31568},
                {0.279372, 0.528821, 0.139506, 0.7892},
                {0.209529, 0.502072, 0.653609, -0.526134}
        };
        Matrix aMatrix = new Matrix(a);
        System.out.println("Hard Q calculated");
        Householder.getQ(aMatrix).print(4, 4);
        System.out.println("Hard Q expected");
        (new Matrix(q)).print(4,4);
    }
}