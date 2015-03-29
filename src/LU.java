import Jama.*;

import java.text.NumberFormat;
import java.util.LinkedList;
public class LU {

    Matrix a;
    int n;
    Matrix finalUpper;
    Matrix finalLower;
    double error = -1;
    NumberFormat nf;

    public LU(Matrix a) {
        this.a = a;
        n = a.getRowDimension();
        nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(8);
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
                    g.add(asdf);
                    for (int k = 0; k < n; k++) {
                        upper[i][k] = upper[i][k] + (temp * upper[j][k]);
                    }
                }
            }
        }

        Matrix tempG = g.remove();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    tempG.set(i, j, -1 * tempG.get(i, j));
                }
            }
        }
        finalLower = tempG;
        while (g.peek() != null) {
            tempG = g.remove();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i != j) {
                        tempG.set(i, j, -1 * tempG.get(i, j));
                    }
                }
            }
            finalLower = Multiply.multiply(finalLower, tempG);
        }
        finalUpper = new Matrix(upper);
        //finalLower.print(nf, 15);
        //finalUpper.print(nf, 15);

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

    public Matrix solve(Matrix b) {
        if (finalLower == null || finalUpper == null) {
            System.out.println("You need to run LU first before solving.");
        } else {
            Matrix y = ForwardSubstitution.forwardSubstitution(finalLower, b);
            return BackwardSubstitution.backSub(finalUpper, y);
        }
            return null;
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
    }
}
