import java.util.Arrays;

/**
 * Abstraction of a Vector
 *
 * @author Patrick Tam
 * @version 0.1
 */
public class Vector {

    private double[] v;

    /**
     * Constructor creates Vector object
     *
     * @param v double representation of vector
     */
    public Vector(double ... v) {
        this.v = v;
    }

    /**
     * Gets a value in the Vector
     *
     * @param i index of value to be retrieved
     * @return value at index
     */
    public double get(int i) {
        return v[i];
    }

    /**
     * Sets a value in the Vector
     * @param i index of the value to set
     * @param v value to insert
     */
    public void set(int i, double v) {
        this.v[i] = v;
    }

    /**
     * Subtracts two vectors
     * this - v
     *
     * @param v vector to subtract
     * @return subtracted vector
     */
    public Vector minus(Vector v) {
        if (this.v.length != v.length()) {
            throw new IllegalArgumentException("You can't subtract vectors with different lengths");
        }
        double[] sub = new double[this.v.length];
        for (int i = 0; i < this.v.length; i++) {
            sub[i] = this.v[i] - v.get(i);
        }
        return new Vector(sub);
    }

    /**
     * Adds two vectors together
     * this + v
     *
     * @param v Vector to add
     * @return this + v
     */
    public Vector plus(Vector v) {
        if (this.v.length != v.length()) {
            throw new IllegalArgumentException("You can't add vectors with different lengths");
        }
        double[] add = new double[this.v.length];
        for (int i = 0; i < this.v.length; i++) {
            add[i] = this.v[i] + v.get(i);
        }
        return new Vector(add);
    }

    /**
     * Gets the dot product of two vectors
     * this dot v
     *
     * @param v vector to dot with
     * @return this dot v
     */
    public double dot(Vector v) {
        if (this.v.length != v.length()) {
            throw new IllegalArgumentException("You can't dot vectors with different lengths");
        }
        double sum = 0;
        for (int i = 0; i < this.v.length; i++) {
            sum += this.v[i] * v.get(i);
        }
        return sum;
    }

    /**
     * Multiplies a Vector by a constant
     *
     * @param c constant to multiply by
     * @return this vector * c
     */
    public Vector times(double c) {
        double[] a = new double[v.length];
        for (int i = 0; i < v.length; i++) {
            a[i] = c * v[i];
        }
        return new Vector(a);
    }

    /**
     * Gets the norm of this Vector
     *
     * @return vector norm
     */
    public double norm() {
        double sum = 0;
        for (int i = 0; i < v.length; i++) {
            sum += Math.pow(v[i],2);
        }
        return Math.pow(sum, 0.5);
    }

    /**
     * Gets backing array
     *
     * @return double array representation of vector
     */
    public double[] getArray() {
        return v;
    }

    /**
     * Gets a copy of the backing array
     */
    public double[] getArrayCopy() {
        double[] a = new double[v.length];
        for (int i = 0; i < v.length; i++) {
            a[i] = v[i];
        }
        return a;
    }

    /**
     * Gets length of vector
     *
     * @return length of vector
     */
    public int length() {
        return v.length;
    }

    /**
     * Prints Vector nicely
     *
     * @return String representation of Vector
     */
    @Override
    public String toString() {
        String output = "[ ";
        for (int i = 0; i < v.length - 1; i++) {
            output += v[i] + ", ";
        }
        return output + v[v.length - 1] + " ]";
    }

    /**
     * Tests equality of this Vector and the given vector
     *
     * @param other Object to compare this vector with
     */
    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (this == other) return true;
        if (!(other instanceof Vector)) return false;
        Vector b = (Vector)other;
        if (b.length() != length()) return false;
        for (int i = 0; i < v.length; i++) {
            if (v[i] != b.get(i)) return false;
        }
        return true;
    }

    /**
     * Gets maximum value in vector
     *
     * @return max value
     */
    public double maxValue() {
        double max = 0;
        for (int i = 0; i < v.length; i++) {
            if (Math.abs(v[i]) > max) {
                max = Math.abs(v[i]);
            }
        }
        return max;
    }
}
