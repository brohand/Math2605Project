import Jama.Matrix;

/**
 * Gets Eigenvalues of a Matrix with Power Method
 *
 * @author Patrick tam
 * @version 0.2
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
        Vector uk1 = oneIteration(a, u0);
        while (uk1.minus(uk).norm() > tol) {
            uk = uk1;
            uk1 = oneIteration(a, uk);
        }
        return uk1;
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
}
