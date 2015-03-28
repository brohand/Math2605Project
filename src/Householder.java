import Jama.Matrix;

/**
 * Factors a Matrix into QR with Householder Reflections
 *
 * @author Patrick Tam
 * @version 0.3
 */
public class Householder {

    /**
     * Gets the Q matrix for QR factorization
     * uses Householders Reflections
     *
     * @param a input matrix
     * @return Matrix representation of Q matrix
     */
    public static Matrix getQ(Matrix a) {
        Matrix curr = a;
        Matrix h1h2h3 = null;
        for (int i = 0; i < a.getColumnDimension() - 1; i++) {
            Matrix H = getHouseholderReflection(curr, i);
            h1h2h3 = (h1h2h3 == null) ? H : h1h2h3.times(H);
            curr = H.times(curr);
        }
        return h1h2h3;
    }

    /**
     * Gets the R matrix for QR factorization
     * uses Householders Reflections
     *
     * @param a input matrix
     * @return Matrix representation of R matrix
     */
    public static Matrix getR(Matrix a) {
        Matrix curr = a;
        for (int i = 0; i < a.getColumnDimension() - 1; i++) {
            curr = getHouseholderReflection(curr, i).times(curr);
        }
        return curr;
    }

    /**
     * Gets a Householder Reflection matrix
     * for the given Matrix at the given column number
     *
     * @param a Matrix to find Householdr reflection of
     * @param col column to find Householder reflection of
     *            (columns start from 0)
     * @return a Householder Reflection matrix
     */
    private static Matrix getHouseholderReflection(Matrix a, int col) {
        //II-2uut
        //II - 2vvt/vmag^2

        //copies input matrix 2d double array
        double[][] input = a.getArrayCopy();

        double[][] output = new double[input.length][input.length];

        //number of columns in Householder Hat Matrix
        int columns = input.length - col;



        //gets identity matrix
        Matrix identity = getIdentityMatrix(columns);

        //gets x
        double[] x1 = new double[columns];
        for (int i = col; i < col + columns; i++ ) {
            x1[i - col] = input[i][col];
        }

        //gets v
        double[] v = x1;
        v[0] += vectorNorm(v);

        //gets VVt
        Matrix VVt = getVVt(v);

        //gets 2uut
        Matrix uut2 = VVt.times(2.0 / Math.pow(vectorNorm(v), 2));

        //gets Hhat matrix as 2d double array
        double[][] hHat = identity.minus(uut2).getArrayCopy();

        //turns Hhat into Householder matrix
        for (int i = input.length - 1; i >= 0; i--) {
            for (int j = input.length - 1; j >= 0; j--) {
                int x = i - col;
                int y = j - col;
                if (x >= 0 && y >= 0) {
                    output[i][j] = hHat[x][y];
                } else if (i == j) {
                    output[i][j] = 1;
                } else {
                    output[i][j] = 0;
                }
            }
        }

        return new Matrix(output);
    }

    /**
     * Gets the V*Vt matrix
     *
     * @param v double array representation of V vector
     * @return Matrix representation of V*Vt matrix
     */
    private static Matrix getVVt(double[] v) {
        double[][] VVtMatrix = new double[v.length][v.length];
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v.length; j++) {
                VVtMatrix[i][j] = v[i] * v[j];
            }
        }
        return new Matrix(VVtMatrix);
    }

    /**
     * Gets the norm of a vector
     *
     * @param v vector as a double array
     * @return vector norm
     */
    private static double vectorNorm(double[] v) {
        double sum = 0;
        for (int i = 0; i < v.length; i++) {
            sum += Math.pow(v[i],2);
        }
        return Math.pow(sum, 0.5);
    }


    /**
     * Gets a square, n x n Identity Matrix
     *
     * @throws IllegalArgumentException if n < 1
     * @param n the number of rows and columns
     * @return an n x n Identity matrix
     */
    private static Matrix getIdentityMatrix(int n) {
        if (n < 1) {
            throw new IllegalArgumentException(
                    "You can't have an identity matrix with less than one row/columns");
        }
        double[][] identity = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    identity[i][j] = 1;
                } else {
                    identity[i][j] = 0;
                }
            }
        }
        return new Matrix(identity);
    }
}