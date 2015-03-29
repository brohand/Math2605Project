import Jama.Matrix;

import java.util.Random;

public class ConvolutionalCodes {

    public static void main(String[] args) {
        encode(5);
    }

    public static void encode(int n) {
        Random rand = new Random();
        double[][] xTemp = new double[n + 3][1];
        for (int i = 0; i < n; i++) {
            if (i >= n) {
                xTemp[i][0] = 0;
            } else {
                int k = rand.nextInt(1 - 0 + 1) + 0;
                xTemp[i][0] = k;
            }
        }

        Matrix x = new Matrix(xTemp);
        Matrix a0 = generateA0(n);
        Matrix a1 = generateA1(n);

        Matrix y0 = Multiply.multiply(a0, x);
        Matrix y1 = Multiply.multiply(a1, x);

        double[][] y0Temp = y0.getArray();
        double[][] y1Temp = y1.getArray();
        for (int i = 0; i < y0.getRowDimension(); i++) {
            y0Temp[i][0] = y0Temp[i][0] % 2;
            y1Temp[i][0] = y1Temp[i][0] % 2;
        }

        System.out.print("The original x vector with specified length " + n + " was: {");
        for (int i = 0; i < x.getRowDimension() - 3; i++) {
            if (i != 0) {
                System.out.print(", ");
            }
            System.out.print((int)x.get(i, 0));
        }
        System.out.print("}");
        System.out.println("");
        System.out.print("The encoded y vector is the following: ");
        System.out.print("{");
        for (int i = 0; i < y0.getRowDimension(); i++) {
            if (i != 0) {
                System.out.print(", ");
            }
            System.out.print((int)y0.get(i, 0) + "" + (int)y1.get(i, 0));
        }
        System.out.print("}");
        System.out.println("");
    }

    public static Matrix generateA0(int n) {
        double[][] temp = new double[n + 3][n + 3];
        for (int i = 0; i < n + 3; i++) {
            int k = i - 3;
            if (k >= 0) {
                temp[i][k] = 1;
            }
            if (k + 1 >= 0) {
                temp[i][k + 1] = 1;
            }
            if (k + 3 >= 0) {
                temp [i][k + 3] = 1;
            }
        }
        return new Matrix(temp);
    }

    public static Matrix generateA1(int n) {
        double[][] temp = new double[n + 3][n + 3];
        for (int i = 0; i < n + 3; i++) {
            int k = i - 3;
            if (k >= 0) {
                temp[i][k] = 1;
            }
            if (k + 2 >= 0) {
                temp[i][k + 2] = 1;
            }
            if (k + 3 >= 0) {
                temp [i][k + 3] = 1;
            }
        }
        return new Matrix(temp);
    }
}
