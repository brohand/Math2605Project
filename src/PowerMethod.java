import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Gets Eigenvalues of a Matrix with Power Method
 *
 * @author Patrick tam
 * @version 0.3
 */
public class PowerMethod {

    public static final int MAX_ITERATIONS = 1000000;

    private static Vector eigenvector = null;
    private static double eigenvalue = Integer.MIN_VALUE;
    private static double eigenvaluePrev = Integer.MIN_VALUE;
    private static int iterations = 0;

    /**
     * Driver for PowerMethod
     *
     * @param args nothing
     */
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        String filename  = "";
        double tol = 1e-17;
        boolean quit = false;
        System.out.println("PowerMethod Driver");
        System.out.println("The Power Method driver allows you to find the EigenValues and Eigennvectors of a Matrix, using the Power Method.");
        System.out.println("NOTE: the .dat input file MUST be in the following format:");
        System.out.println(" - rows of doubles separated by either commas or spaces");
        System.out.println(" - separate rows are separated by new lines");
        System.out.println("\nFor Example:\n1.0,2.\n3,4e2");
        while (!quit) {
            try {
                System.out.println("\nEnter the filename of your .dat file");
                filename = console.nextLine();
                System.out.println("Enter the tolerance");
                tol = Double.parseDouble(console.nextLine());
                try {
                    PowerMethod.powerMethod(DatParser.datToMatrix(new File(filename)), tol);
                    Vector eigenvector = PowerMethod.getEigenvector();
                    int iterations = PowerMethod.getIterations();
                    double eigenvalue = PowerMethod.getEigenvalue();
                    System.out.println("\nIterations: " + iterations);
                    System.out.println("Eigenvalue: " + eigenvalue);
                    System.out.println("Eigenvector: " + eigenvector);
                } catch (FileNotFoundException e) {
                    System.out.println("File Not Found: " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("Data File Invalid: " + e.getMessage());
                }
                System.out.println("\nContinue? [y/n]");
                String in = console.nextLine();
                if (in.equals("n")) {
                    quit = true;
                }
                System.out.println("");
            } catch(NumberFormatException e) {
                System.out.println("Invalid Input. Please retry \n");
            } catch(Exception e) {
                e.getMessage();
            }
        }
        System.out.println("y");
    }

    /**
     * Uses Power Method to get the
     *  - maximum eigenvalue
     *  - corresponding eigenvector
     *  - the number of iterations
     *  the initial guess vector is set to 1
     *
     * @param a input Matrix
     * @param tol tolerance
     * @return max eigenvalue
     */
    public static void powerMethod(Matrix a, double tol) {
        double[] u0 = new double[a.getRowDimension()];
        for (int i = 0; i < u0.length; i++) {
            u0[i] = 1;
        }
        powerMethod(a, tol, new Vector(u0));
    }

    /**
     * Uses Power Method to get the
     *  - maximum eigenvalue
     *  - corresponding eigenvector
     *  - the number of iterations
     *
     * @param a input Matrix
     * @param tol tolerance
     * @param u0 intial guess vector
     * @return max eigenvalue
     */
    public static void powerMethod(Matrix a, double tol, Vector u0) {
        Vector uk = u0;
        Vector uk1 = oneIteration(a, u0);
        int i = 1;
        while (Math.abs(eigenvaluePrev - eigenvalue) > tol) {
            if (i >= MAX_ITERATIONS) {
                throw new PowerMethodException("Failed to converge after " + i + " iterations.");
            }
            i++;
            uk = uk1;
            uk1 = oneIteration(a, uk);
        }
        eigenvector = uk1;
        iterations = i;
    }

    /**
     * Runs one iteration of the Power Method
     * Uk+1 = (1/alphak) penis
     *AUk
     * @param a Matrix to find eigenvalues for
     * @param uk the starting vector
     * @return double as vector representation of an iteration of Power method
     */
    private static Vector oneIteration(Matrix a,  Vector uk) {
        eigenvaluePrev = eigenvalue;
        Vector Auk = a.times(uk);
        eigenvalue = Auk.maxValue();
        return Auk.times(1.0 / Auk.maxValue());
    }

    /**
     * Gets an already calculated eigenvector
     *
     * @throws PowerMethodException if eigenvector hasn't been created yet
     * @return eigenvector
     */
    public static Vector getEigenvector() {
        if (eigenvector == null) {
            throw new PowerMethodException("Eigenvector has not been initialized.");
        }
        return eigenvector;
    }

    /**
     * Gets an already calculated eigenvalue
     *
     * @throws PowerMethodException if eigenvalue hasn't been created yet
     * @return eigenvalue
     */
    public static double getEigenvalue() {
        if (eigenvalue == Integer.MIN_VALUE) {
            throw new PowerMethodException("Eigenvalue has not been initialized.");
        }
        return eigenvalue;
    }

    /**
     * Gets an already calculated number of iterations
     *
     * @throws PowerMethodException if iterations hasn't been created yet
     * @return iterations
     */
    public static int getIterations() {
        if (iterations == 0l) {
            throw new PowerMethodException("Iterations has not been initialized.");
        }
        return iterations;
    }
}
