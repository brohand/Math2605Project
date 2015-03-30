/**
 * Gets Eigenvalues of a Matrix with Power Method
 *
 * @author Patrick tam
 * @version 0.2
 */
public class PowerMethod {

    public static final int MAX_ITERATIONS = 1000000;

    private static Vector eigenvector = null;
    private static double eigenvalue = Integer.MIN_VALUE;
    private static double eigenvaluePrev = Integer.MIN_VALUE;
    private static int iterations = 0;


    /**
     * Uses Power Method to get the
     *  - maximum eigenvalue
     *  - corresponding eigenvector
     *  - the number of iterations
     *
     * @param a input Matrix
     * @return max eigenvalue
     */
    public static void powerMethod(Matrix a, double tol, Vector u0) {
        Vector uk = u0;
        Vector uk1 = oneIteration(a, u0);
        int i = 1;
        while (Math.abs(eigenvaluePrev - eigenvalue) > tol) {
            if (i >= MAX_ITERATIONS) {
                throw new PowerMethodException("Failed to converge after " + i + " iterations.");
            }
            i++;
            uk = uk1;
            uk1 = oneIteration(a, uk);
        }
        eigenvector = uk1;
        iterations = i;
    }

    /**
     * Runs one iteration of the Power Method
     * Uk+1 = (1/alphak)AUk
     *
     * @param a Matrix to find eigenvalues for
     * @param uk the starting vector
     * @return double as vector representation of an iteration of Power method
     */
    private static Vector oneIteration(Matrix a,  Vector uk) {
        eigenvaluePrev = eigenvalue;
        Vector Auk = a.times(uk);
        eigenvalue = Auk.maxValue();
        return Auk.times(1.0 / Auk.maxValue());
    }

    /**
     * Gets an already calculated eigenvector
     *
     * @throws PowerMethodException if eigenvector hasn't been created yet
     * @return eigenvector
     */
    public static Vector getEigenvector() {
        if (eigenvector == null) {
            throw new PowerMethodException("Eigenvector has not been initialized.");
        }
        return eigenvector;
    }

    /**
     * Gets an already calculated eigenvalue
     *
     * @throws PowerMethodException if eigenvalue hasn't been created yet
     * @return eigenvalue
     */
    public static double getEigenvalue() {
        if (eigenvalue == Integer.MIN_VALUE) {
            throw new PowerMethodException("Eigenvalue has not been initialized.");
        }
        return eigenvalue;
    }

    /**
     * Gets an already calculated number of iterations
     *
     * @throws PowerMethodException if iterations hasn't been created yet
     * @return iterations
     */
    public static int getIterations() {
        if (iterations == 0l) {
            throw new PowerMethodException("Iterations has not been initialized.");
        }
        return iterations;
    }
}
