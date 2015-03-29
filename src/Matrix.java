import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Representation of a Matrix
 *
 * @author Patrick Tam
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
            throw new MatrixException("You can't subtract matrices with different dimensions");
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
            throw new MatrixException("You can't multiply a matrix by a null vector");
        }
        double[] v = a.getArray();
        if(m.length != v.length) {
            throw new MatrixException("You can't multiply a Matrix and Vector with different lengths");
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

    //not my code
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
