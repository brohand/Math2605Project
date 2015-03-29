/**
 * Calculates a Leslie Matrix
 */
public class Leslie {

    public static void runSimulation(Matrix a, Vector xk, int iterations) {
        for (int i = 1; i < iterations; i++) {
            xk = Multiply.matrixTimesVector(a, xk);
        }
    }

    public static Vector oneIteration(Matrix aM, Vector vV) {
       return null;
    }
}
