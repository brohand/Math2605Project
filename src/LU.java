import Jama.*;
import java.util.LinkedList;
public class LU {

    Matrix a;
    int n;
    Matrix finalUpper;
    Matrix finalLower;
    double error = -1;

    public LU(Matrix a) {
        this.a = a;
        n = a.getRowDimension();
    }

    public void lu_fact() {
        LinkedList<Matrix> g = new LinkedList<Matrix>();
        double [][]original = a.getArrayCopy();
        double [][]upper = original;
        double [][]identity = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    identity[i][j] = 1;
                }
            }
        }
        double [][]lower = identity;

        for (int j = 0; j < n; j++) {
            for (int i = 1; i < n; i++) {
                if (j < i) {
                    double temp = -1 * upper[i][j] / upper[j][j];
                    System.out.println(upper[i][j]);
                    System.out.println(upper[j][j]);
                    System.out.println(temp);
                    double[][] temporary = new double[n][n];
                    for (int m = 0; m < n; m++) {
                        for (int p = 0; p < n; p++) {
                            if (m == p) {
                                temporary[m][p] = 1;
                            }
                        }
                    }
                    temporary[i][j] = temp;
                    Matrix asdf = new Matrix(temporary);
                    asdf.print(n, n);
                    g.add(asdf);
                    for (int k = 0; k < n; k++) {
                        upper[i][k] = upper[i][k] + (temp * upper[j][k]);
                    }
                }
            }
        }

        finalLower = g.remove().inverse();
        while (g.peek() != null) {
            finalLower = Multiply.multiply(finalLower, g.remove().inverse());
        }
        finalUpper = new Matrix(upper);

        finalLower.print(n, n);
        finalUpper.print(n, n);

    }

    public Matrix getUpper() {
        return finalUpper;
    }

    public Matrix getLower() {
        return finalLower;
    }

    public Matrix getOriginal() {
        return a;
    }

    public void solve(Matrix y) {
        if (finalLower == null || finalUpper == null) {
            System.out.println("You need to run LU first before solving.");
        } else {

        }

    }

    public double getError() {
        Matrix temp = Multiply.multiply(finalLower, finalUpper);
        return Norm.getNorm(temp.minus(a));
    }

    public static void main(String[] args) {
        double [][]test = new double[4][4];
        test[0][0] = 1;
        test[0][1] = 0.5;
        test[0][2] = 0.333333;
        test[0][3] = 0.25;
        test[1][0] = 0.5;
        test[1][1] = 0.333333;
        test[1][2] = 0.25;
        test[1][3] = 0.2;
        test[2][0] = 0.333333;
        test[2][1] = 0.25;
        test[2][2] = 0.2;
        test[2][3] = 0.166667;
        test[3][0] = 0.25;
        test[3][1] = 0.2;
        test[3][2] = 0.166667;
        test[3][3] = 0.142857;


        Matrix a = new Matrix(test);
        LU testing = new LU(a);
        testing.lu_fact();

        System.out.println(testing.getError());
    }
}
