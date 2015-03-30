import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * PowerMethod Driver
 * finds the eigenvalues and eigenvectors for a given Matrix
 *
 * @author Patrick Tam
 * @version 0.1
 */
public class PowerMethodDriver {

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
            }
        }
    }
}
