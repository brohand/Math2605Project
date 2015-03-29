import Jama.Matrix;

/**
 * Gets Eigenvalues of a Matrix with Power Method
 *
 * @author Patrick tam
 * @version 0.1
 */
public class PowerMethod {

    /**
     * Uses Power Method to get the maximum eigenvalue
     *
     * @param a input Matrix
     * @return max eigenvalue
     */
    public static Vector getEigenvector(Matrix a, double tol, Vector u0) {
        Vector uk = u0;
        Vector uk1 = new Vector(oneIteration(a, uk.getArray()));
        while (uk1.minus(uk).norm() > tol) {
            uk = uk1;
            uk1 = new Vector(oneIteration(a, uk.getArray()));
        }
        return uk1;
    }

    /**
     * Runs one iteration of the Power Method
     * Uk+1 = (1/alphak)AUk
     *
     * @param a Matrix to find eigenvalues for
     * @param u the starting vector
     * @return double as vector representation of an iteration of Power method
     */
    private static double[] oneIteration(Matrix a,  double[] u) {
        double[] Auk = matrixTimesVector(a, u);
        double alpha = u[0];
        for (int i = 0; i < Auk.length; i++) {
            Auk[i] *= 1.0 / alpha;
        }
        return Auk;
    }
    /**
     * Matrix * Vector
     *
     * @param aMatrix input Matrix
     * @param v input Vector
     * @return Matrix * Vector
     */
    private static double[] matrixTimesVector(Matrix aMatrix, double[] v) {
        double[][] a = aMatrix.getArrayCopy();
        double[] vf = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                vf[i] += a[i][j] * v[i];
            }
        }
        return vf;
    }
}
