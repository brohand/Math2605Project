/**
 * Created by super_000 on 3/9/2015.
 */
import Jama.*;
import java.text.NumberFormat;
import java.util.Scanner;
public class Driver {
    private static Scanner darkly = new Scanner(System.in);
    public static void main(String[] args) {

        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(5);

        System.out.println("HILBERT ERROR VISUALIZATION PROGRAM ULTIMATE ARCADE EDITION v0.0000001");
        System.out.println("CHOOSE THY FATE");
        System.out.println("1. QR Visualization");
        System.out.println("2. LU Visualization");
        int fate = darkly.nextInt();
        if (fate == 1) {
            qrVisualizer();
        }else if (fate == 2) {
            luVisualizer();
        } else {
            System.out.println("Fuck you");
        }





        //LUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU

    }

    public static Matrix createHilbert(int rows, int columns) {
        double[][] hilbert = new double[rows][columns];
        for(int i = 0; i < rows; i++) {
            for (int j =0; j < columns; j++) {
                hilbert[i][j] = 1. / (i + j + 1.);
            }
        }
        return new Matrix(hilbert);
    }

    public static Matrix createB(int size) {
        double[][] b = new double[size][1];
        for (int i = 0; i < size; i++) {
            b[i][0] = Math.pow(0.1, (double)size/3);
        }
        return new Matrix(b);
    }



    public static void qrVisualizer() {
        System.out.println("QR Factorization for Hilbert Matricies");
        System.out.println("");

        for(int n = 2; n <= 20; n++) {
            Matrix hilbert = createHilbert(n, n);
            Matrix b = createB(n);
            Matrix xSol = solve_qr_b.Solve(hilbert, b);
            System.out.println("For Hilbert of size " + n + ":");
            System.out.println("Xsol = ");
            xSol.print(n , n);
            System.out.print("------");
            System.out.print("||QR - H|| = " + Householder.error(hilbert));
            System.out.println("------ ||HXsol - b||" + Norm.getNorm(Multiply.multiply(hilbert, xSol).minus(b)));



        }

    }

    public static void luVisualizer() {
        System.out.println("LU Factorization for Hilbert Matricies");
        System.out.println("");
        for (int n = 2; n <= 20; n++) {
            Matrix hilbert = createHilbert(n, n);
            Matrix b = createB(n);
            LU lu = new LU(hilbert);
            lu.lu_fact();
            System.out.println("The error for a hilbert matrix of size " + n + " is " + lu.getError());
            Matrix c = lu.solve(b);
            System.out.print("The solution, x, for LU is ");
            c.print(c.getRowDimension(), c.getColumnDimension());
            System.out.println("");
            System.out.print("The error, Hx - b, for LU is ");
            Matrix e = Multiply.multiply(hilbert, c).minus(b);
            System.out.print(Norm.getNorm(e));
        }
    }


}
