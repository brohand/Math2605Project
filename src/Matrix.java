import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Representation of a Matrix
 *
 * @author Patrick Tam, Jinsong Han/Aaron Andrews
 * @version 0.1
 */
public class Matrix {

    private double[][] m;

    /**
     * Creates an empty Matris of dimensions i x j
     *
     * @param i (m) row count
     * @param j (n) column count
     */
    public Matrix(int i, int j) {
        this(new double[i][j]);
    }

    /**
     * Creates a Matrix form the given 2d array
     *
     * @throws IllegalArgumentException if the input array is null, empty, or, or ragged
     * @param m array to turn into a matrix
     */
    public Matrix(double[][] m) {
        if (m == null) {
            throw new IllegalArgumentException("The input array CAN NOT be null.");
        }
        if (m.length == 0) {
            throw new IllegalArgumentException("The input array CAN NOT be empty.");
        }
        for (int i = 0; i < m.length; i++) {
           if (m[0].length != m[i].length) {
               throw new IllegalArgumentException("The input array CAN NOT be ragged");
           }
        }
        this.m = m;
    }

    /**
     * Gets a value from the Matrix at the given coordinates
     *
     * @throws IllegalArgumentException if the coordinates are out of bounds
     * @param i row number
     * @param j column number
     * @return double value at i,j
     */
    public double get(int i, int j) {
        if (i >= m.length || j >= m[0].length) {
            throw new IllegalArgumentException("The specified coordinates are out of bounds");
        }
        return m[i][j];
    }

    /**
     * Sets a value in the Matrix at the given coordinates
     *
     * @throws IllegalArgumentException if the coordinates are out of bounds
     * @param i row number
     * @param j column number
     * @param x value to insert into matrix
     */
    public void set(int i, int j, double x) {
        if (i >= m.length || j >= m[0].length) {
            throw new IllegalArgumentException("The specified coordinates are out of bounds");
        }
        m[i][j] = x;
    }

    /**
     * Adds two matrices together
     *
     * @throws IllegalArgumentException if the matrix is null or dimensions differ
     * @param aM Matrix to add to this one
     * @return this matrix + aM
     */
    public Matrix plus(Matrix aM) {
        if (aM == null) {
            throw new IllegalArgumentException("You can't add a null matrix");
        }
        if (aM.getColumnDimension() != getColumnDimension() || aM.getRowDimension() != getRowDimension()) {
            throw new IllegalArgumentException("You can't add matrices with different dimensions");
        }
        double[][] a = aM.getArray();
        double[][] add = new double[m.length][m[0].length];
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                add[i][j] = m[i][j] + a[i][j];
            }
        }
        return new Matrix(add);
    }

    /**
     * Subtracts the given matrix from this matrix
     *
     * @throws IllegalArgumentException if the matrix is null or dimensions differ
     * @param aM Matrix to subtract from this one
     * @return this matrix - aM
     */
    public Matrix minus(Matrix aM) {
        if (aM == null) {
            throw new IllegalArgumentException("You can't subtract a null matrix");
        }
        if (aM.getColumnDimension() != getColumnDimension() || aM.getRowDimension() != getRowDimension()) {
            throw new IllegalArgumentException("You can't subtract matrices with different dimensions");
        }
        double[][] a = aM.getArray();
        double[][] sub = new double[m.length][m[0].length];
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                sub[i][j] = m[i][j] - a[i][j];
            }
        }
        return new Matrix(sub);
    }

    /**
     * Multiplies this Matrix by a scalar value
     *
     * @param c constant to multiply Matrix by
     * @return c * A
     */
    public Matrix times(double c) {
        double[][] times = new double[m.length][m[0].length];
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                times[i][j] = m[i][j] * c;
            }
        }
        return new Matrix(times);
    }

    /**
     * Multiplies two Matrices together
     *
     * @throws IllegalArgumentException if the input matrix is null or dimensions differ
     * @param bM Matrix to multiply this one by
     * @return A * B
     */
    public Matrix times(Matrix bM) {
        if (bM == null) {
            throw new IllegalArgumentException("You can't multiply null matrices.");
        }
        double[][] a = getArray();
        double[][] b = bM.getArray();
        if (a[0].length != b.length) {
            throw new IllegalArgumentException("If a is m x n, b MUST be n x p");
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
     * @param a input Vector
     * @return Matrix * Vector
     */
    public Vector times(Vector a) {
        if(a == null) {
            throw new IllegalArgumentException("You can't multiply a matrix by a null vector");
        }
        double[] v = a.getArray();
        if(m.length != v.length) {
            throw new IllegalArgumentException("You can't multiply a Matrix and Vector with different lengths");
        }
        double[] vf = new double[m.length];
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                vf[i] += m[i][j] * v[j];
            }
        }
        return new Vector(vf);
    }

    /**
     * Tranposes this Matrix
     *
     * @return A ^ T
     */
    public Matrix transpose() {
        double[][] t = new double[m[0].length][m.length];
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                t[j][i] = m[i][j];
            }
        }
        return new Matrix(t);
    }

    /**
     * Gets the norm of the Matrix
     * norm is defined as absolute value of max entry in Matrix
     *
     * @return norm of the Matrix
     */
    public double norm() {
        double max = 0;
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                if (Math.abs(m[i][j]) > max) {
                    max = Math.abs(m[i][j]);
                }
            }
        }
        return max;
    }

    /**
     * Performs a forwards substitution to solve Ax=b
     * on a Lower triangular Matrix
     *
     * @param bV the answer Vector
     * @return x the solution Vector
     */
    public Vector forwardsSubstitution(Vector bV) {
        if (getRowDimension() < 2 || getColumnDimension() < 2) {
            throw new IllegalArgumentException("The Matrix must have greater than 1 row/column");
        }

        if (getRowDimension() != bV.length()) {
            throw new IllegalArgumentException("You can't input a Vector with a different length then the Matrix");
        }
        for (int i = 0; i < getRowDimension(); i++) {
            for (int j = getColumnDimension() - 1; j > i; j--) {
                if (get(i, j) != 0) {
                    throw new IllegalArgumentException("The entered Matrix is NOT lower triangular.");
                }
            }
        }
        /**
         * Originally part of the ForwardSubstitution class
         *
         * @author Jinsong Han
         */
        double[][] a = getArrayCopy();
        double[] b = bV.getArrayCopy();
        double[] x = new double[getRowDimension()];
        for (int j = 0; j < getRowDimension(); j++) {
            for (int k = 0; k < getColumnDimension(); k++) {
                if (k == 0 && j == 0) {
                    x[j] = b[j] / a[j][k];
                } else {
                    if (k == j) {
                        double temp = 0;
                        for (int l = k - 1; l >= 0; l--) {
                            temp = temp + (x[l] * a[j][l]);
                        }
                        x[j] = (b[k] - temp) / a[j][k];
                    }
                }
            }
        }
        return new Vector(x);
    }

    /**
     * Performs a backwards substitution to solve Ax=b
     * the current Matrix MUST BE Upper triangular
     * @param b the answer Vector
     * @return x the solution Vector
     */
    public Vector backwardsSubstitution(Vector b) {
        if (getRowDimension() < 2 || getColumnDimension() < 2) {
            throw new IllegalArgumentException("The Matrix must have greater than 1 row/column");
        }
        for (int i = 1; i < getRowDimension(); i++) {
            for (int j = 0; j < i; j++) {
                if (get(i, j) != 0) {
                    throw new IllegalArgumentException("The entered Matrix is NOT upper triangular");
                }
            }
        }

        if (getRowDimension() != b.length()) {
            throw new IllegalArgumentException("You can't input a Vector with a different length then the Matrix");
        }

        /**
         * Originally part of the BackwardSubstitution class
         *
         * @author Aaron Andrews
         */
        double[] sol = new double[getColumnDimension()];

        for(int row = getRowDimension() - 1; row >= 0; row--) {
            double currPiv = get(row, row);
            double x = b.get(row) / currPiv;
            for(int i = 0; i < getColumnDimension(); i++) {
                double mod = 1;
                if(i != row) {
                    if(i != 0 && i < b.length()) {
                        mod = sol[i];
                    }
                    x -= mod*get(row, i) / currPiv;
                }
            }
            sol[row] = x;
        }
        return new Vector(sol);
    }

    /**
     * Gets the original Matrix from an augmented Matrix
     *
     * @throws IllegalArgumentException if Matrix is not augmented
     * @return regular Matrix
     */
    public Matrix getAugmentedMatrix() {
        if (getColumnDimension() < 2) {
            throw new IllegalArgumentException("This Matrix is not augmented.");
        }
        Matrix A = new Matrix(getRowDimension(), getColumnDimension() - 1);
        for (int i = 0; i < A.getRowDimension(); i++) {
            for (int j = 0; j < A.getColumnDimension(); j++) {
                A.set(i,j, get(i,j));
            }
        }
        return A;
    }

    /**
     * Gets the original Vector from an augmented Matrix
     *
     * @return regular vector
     */
    public Vector getAugmentedVector() {
        Vector V = new Vector(getRowDimension());
        for (int i = 0; i < getRowDimension(); i++) {
            V.set(i, get(i, getColumnDimension() - 1));
        }
        return V;
    }

    /**
     * Gets the number of rows
     *
     * @return number of rows
     */
    public int getRowDimension() {
        return m.length;
    }

    /**
     * Gets the number of columns
     *
     * @return number of columns
     */
    public int getColumnDimension() {
        return m[0].length;
    }

    /**
     * Returns the backing array
     *
     * @return backing array
     */
    public double[][] getArray() {
        return m;
    }

    /**
     * Returns a copy of the backing array
     *
     * @return copy of backing array
     */
    public double[][] getArrayCopy() {
        double[][] copy = new double[m.length][m[0].length];
        for (int i = 0; i < copy.length; i++) {
            for (int j = 0; j < copy[i].length; j++) {
                copy[i][j] = m[i][j];
            }
        }
        return copy;
    }

    /**
     * Equals method
     *
     * @param other Object
     * @return True if matrices are equal, false otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (this == other) return true;
        if (!(other instanceof Matrix)) return false;
        Matrix b = (Matrix)other;
        if (m.length != b.getArray().length || m[0].length != b.getArray()[0].length) return false;
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                if (m[i][j] != b.get(i,j)) return false;
            }
        }
        return true;
    }

    /**
     * All the code below is from NIST's JAMA library
     *
     * @url http://math.nist.gov/javanumerics/jama/
     */

    public void print(int var1, int var2) {
        this.print(new PrintWriter(System.out, true), var1, var2);
    }

    public void print(PrintWriter var1, int var2, int var3) {
        DecimalFormat var4 = new DecimalFormat();
        var4.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.US));
        var4.setMinimumIntegerDigits(1);
        var4.setMaximumFractionDigits(var3);
        var4.setMinimumFractionDigits(var3);
        var4.setGroupingUsed(false);
        this.print(var1, var4, var2 + 2);
    }

    public void print(NumberFormat var1, int var2) {
        this.print(new PrintWriter(System.out, true), var1, var2);
    }

    public void print(PrintWriter var1, NumberFormat var2, int var3) {
        var1.println();

        double[][] A = m;
        int m = this.m.length;
        int n = this.m[0].length;

        for(int var4 = 0; var4 < m; ++var4) {
            for(int var5 = 0; var5 < n; ++var5) {
                String var6 = var2.format(A[var4][var5]);
                int var7 = Math.max(1, var3 - var6.length());

                for(int var8 = 0; var8 < var7; ++var8) {
                    var1.print(' ');
                }

                var1.print(var6);
            }

            var1.println();
        }

        var1.println();
    }

}
