import Jama.Matrix;

/**
 * Multiplies two matrices together
 *
 * @author Patrick Tam
 * @version 0.1
 */
public class Multiply {

    /**
     * Multiplies two matrices together
     * a*b
     *
     * @param aM first matrix to multiply
     * @param bM second matrix to multiply
     * @return a * b
     */
    public static Matrix multiply(Matrix aM, Matrix bM) {
        if (aM == null || bM == null) {
            throw new MatrixException("You can't multiply null matrices.");
        }
        double[][] a = aM.getArray();
        double[][] b = bM.getArray();
        if (a.length == 0 || a[0].length == 0 || b.length == 0 || b[0].length == 0) {
            throw new MatrixException("You can't multiply empty matrices");
        }
        if (a[0].length != b.length) {
            throw new MatrixException("If a is m x n, b MUST be n x p");
        }

        double[][] c = new double[a.length][b[0].length];

        for (int i = 0; i < c.length; i++) {
            for (int j = 0; j < c[0].length; j++) {
                for (int k = 0; k < a[0].length; k++) {
                    c[i][j] += a[i][k] * b[k][j];
                }
            }
        }

        return new Matrix(c);
    }

    /**
     * Matrix * Vector
     *
     * @param aMatrix input Matrix
     * @param vVector input Vector
     * @return Matrix * Vector
     */
    public static Vector matrixTimesVector(Matrix aMatrix, Vector vVector) {
        if(aMatrix == null || vVector == null) {
            throw new MatrixException("You can't multiply a null matrices or vectors.");
        }
        double[][] a = aMatrix.getArray();
        double[] v = vVector.getArray();
        if(a.length != v.length) {
            throw new MatrixException("You can't multiply a Matrix and Vector with different lengths");
        }
        double[] vf = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                vf[i] += a[i][j] * v[j];
            }
        }
        return new Vector(vf);
    }
}