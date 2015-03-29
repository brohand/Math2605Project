import java.util.Random;
import java.util.Scanner;

public class ConvolutionalCodes {

    private static Scanner kbReader = new Scanner(System.in);

    public static void main(String[] args) {
        boolean keepLooping = true;
        System.out.println("CONVOLUTIONAL CODES SUPER EXTRA COMPLETE 100% PLUS DLC VERSION");
        do {
            System.out.println("");
            System.out.println("CHOOSE THY FATE");
            System.out.println("1. Encoding");
            System.out.println("2. Decoding");
            System.out.println("3. Iterative Algorithm Testing");
            System.out.println("4. Quit");
            System.out.print("Your selection? ");
            int fate = kbReader.nextInt();
            System.out.println("");
            if (fate == 1) {
                System.out.println("Encoding selected.");
                System.out.print("Enter the length of the stream you would like to generate: ");
                int length = kbReader.nextInt();
                System.out.println("");
                encode(length);
            } else if (fate == 2) {
                boolean invalid = true;
                System.out.println("Decoding selected.");
                while (invalid) {
                    System.out.println("");
                    System.out.print("Enter the stream that you would like to decode: ");
                    String temp = kbReader.next();
                    System.out.println("");
                    if (temp.length() % 2 != 0) {
                        System.out.println("ERROR: The stream entered must have a even length.");
                    } else {
                        System.out.println("Which iterative method would you like to decode using?");
                        System.out.println("1. Jacobi");
                        System.out.println("2. Gauss-Seidel");
                        System.out.println("3. Both");
                        System.out.print("Method: ");
                        int method = kbReader.nextInt();
                        if (method < 1 || method > 3) {
                            System.out.println("Invalid method.");
                        } else {
                            System.out.println("");
                            System.out.print("Enter your error tolerance (enter -1 for default, 10^-8): ");
                            double tol = kbReader.nextDouble();
                            if (tol <= 0) {
                                tol = 1.0 / 100000000.0;
                            }
                            System.out.println("The recorded tolerance is " + tol);
                            System.out.println("");
                            decode(temp, method, tol);
                            invalid = false;
                        }
                    }
                }
            } else if (fate == 3) {

            } else if (fate == 4) {
                System.out.println("fuck you");
                keepLooping = false;
            } else {
                System.out.println("Invalid response.");
            }


        } while (keepLooping);
    }

    public static String decode(String n, int method, double tol) {
        System.out.println("The program will now attempt to decode the sequence, " + n + ".");
        Matrix y0 = new Matrix(n.length() / 2, 1);
        Matrix y1 = new Matrix(n.length() / 2, 1);
        String firstY = "";
        String secondY = "";
        for (int i = 0; i < n.length(); i++) {
            if (i % 2 == 0) {
                y0.set(i / 2, 0, Character.getNumericValue(n.charAt(i)));
                firstY = firstY + n.charAt(i);
            } else {
                y1.set(i / 2, 0, Character.getNumericValue(n.charAt(i)));
                secondY = secondY + n.charAt(i);
            }
        }

        System.out.println("The decoded Y0 is: " + firstY);
        System.out.println("The decoded Y1 is: " + secondY);

        Matrix a0 = generateA0(y0.getRowDimension() - 3);
        Matrix a1 = generateA1(y1.getRowDimension() - 3);
        Matrix x0 = new Matrix(y0.getRowDimension(), 1);
        for (int i = 0; i < x0.getRowDimension(); i++) {
            x0.set(i, 0, 0);
        }

        String xJ = "";
        String xG = "";

        if (method == 1 || method == 3) {
            System.out.println("------------------------------");
            System.out.println("Jacobi Algorithm:");
            Jacobi j = new Jacobi(a0, y0, x0, tol);
            System.out.print("The estimation for decoding the string, " + n + ", using y0 is: ");
            Matrix xJacobi = j.jacobi();

            for (int i = 0; i < xJacobi.getRowDimension() - 3; i++) {
                xJ = xJ + "" + (Math.abs((int) xJacobi.get(i, 0) % 2));
            }
            System.out.println(xJ);
            System.out.println("The iteration took " + j.getIterations() + " iterations.");
            if (j.getIterations() >= j.getMaxIterations()) {
                System.out.println("The iteration was terminated because it hit the max number of iterations allowed.");
            }

            j = new Jacobi(a1, y1, x0, tol);
            System.out.print("The estimation for decoding the string, " + n + ", using y1 is: ");
            xJacobi = j.jacobi();
            xJ = "";
            for (int i = 0; i < xJacobi.getRowDimension() - 3; i++) {
                xJ = xJ + "" + (Math.abs((int) xJacobi.get(i, 0) % 2));
            }
            System.out.println(xJ);
            System.out.println("The iteration took " + j.getIterations() + " iterations.");
            if (j.getIterations() >= j.getMaxIterations()) {
                System.out.println("The iteration was terminated because it hit the max number of iterations allowed.");
            }
            if (method == 1) {
                return xJ;
            }
        }

        if (method == 2 || method == 3) {
            System.out.println("------------------------------");
            System.out.println("Gauss-Seidel Algorithm:");
            GaussSeidel g = new GaussSeidel(a0, y0, x0, tol);
            System.out.print("The estimation for decoding the string, " + n + ", using y0 is: ");
            Matrix xGauss = g.gauss_seidel();

            for (int i = 0; i < xGauss.getRowDimension() - 3; i++) {
                xG = xG + "" + (Math.abs((int)xGauss.get(i, 0) % 2));
            }
            System.out.println(xG);
            System.out.println("The iteration took " + g.getIterations() + " iterations.");
            if (g.getIterations() >= g.getMaxIterations()) {
                System.out.println("The iteration was terminated because it hit the max number of iterations allowed.");
            }

            g = new GaussSeidel(a1, y1, x0, tol);
            System.out.print("The estimation for decoding the string, " + n + ", using y1 is: ");
            xGauss = g.gauss_seidel();
            xG = "";
            for (int i = 0; i < xGauss.getRowDimension() - 3; i++) {
                xG = xG + "" + (Math.abs((int)xGauss.get(i, 0) % 2));
            }
            System.out.println(xG);
            System.out.println("The iteration took " + g.getIterations() + " iterations.");
            if (g.getIterations() >= g.getMaxIterations()) {
                System.out.println("The iteration was terminated because it hit the max number of iterations allowed.");
            }
            if (method == 2) {
                return xG;
            }
        }

        System.out.println("");
        System.out.println("The Gauss-Seidel approximation has been returned to the console.");
        return xG;
    }

    public static String encode(int n) {
        System.out.println("The program will now generate a random x vector to encode, with length " + n + ".");
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
        String y = "";
        for (int i = 0; i < y0.getRowDimension(); i++) {
            if (i != 0) {
                System.out.print(", ");
            }
            System.out.print((int)y0.get(i, 0) + "" + (int)y1.get(i, 0));
            y = y + "" + (int)y0.get(i, 0) + "" + (int)y1.get(i, 0);
        }
        System.out.print("}");
        System.out.println("");
        System.out.println("The string format of the y word is: " + y);

        return y;
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
