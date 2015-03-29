import Jama.Matrix;

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
        while (uk1.minus(uk).norm() > tol) {
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
        Vector Auk = matrixTimesVector(a, uk);
        eigenvalue = Auk.maxValue();
        return Auk.times(1.0 / Auk.maxValue());
    }
    /**
     * Matrix * Vector
     *
     * @param aMatrix input Matrix
     * @param v input Vector
     * @return Matrix * Vector
     */
    private static Vector matrixTimesVector(Matrix aMatrix, Vector v) {
        double[][] a = aMatrix.getArrayCopy();
        double[] vf = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                vf[i] += a[i][j] * v.get(j);
            }
        }
        return new Vector(vf);
    }

    public static Vector getEigenvector() {
        if (eigenvector == null) {
            throw new PowerMethodException("Eigenvector has not been initialized.");
        }
        return eigenvector;
    }

    public static double getEigenvalue() {
        if (eigenvalue == Integer.MIN_VALUE) {
            throw new PowerMethodException("Eigenvalue has not been initialized.");
        }
        return eigenvalue;
    }

    public static int getIterations() {
        if (iterations == 0l) {
            throw new PowerMethodException("Iterations has not been initialized.");
        }
        return iterations;
    }
}
