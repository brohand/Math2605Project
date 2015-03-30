/**
 * Calculates a Leslie Matrix
 *
 * @author Patrick Tam
 * @version 0.1
 */
public class Leslie {

    public static void main(String[] args) {
        double[][] a = {
                {0, 1.2,1.1, .9, .1, 0, 0, 0, 0},
                {.7, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, .85, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, .9, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, .9, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, .88, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, .8, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, .77, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, .40, 0}
        };
        Matrix A = new Matrix(a);
        Vector x0 = new Vector(2.1, 1.9, 1.8, 2.1, 1.0, 1.7, 1.2, 0.9, 0.5);
        x0 = x0.times(Math.pow(10,5));
        runSimulation(A, x0, 10);
    }

    public static void runSimulation(Matrix a, Vector xk, int iterations) {
        System.out.println("Iteration: 0 Vector: " + xk);
        for (int i = 1; i <= iterations; i++) {
            xk = a.times(xk);
            System.out.println("Iteration: " + i + " Vector: " + xk);
        }
    }
}
