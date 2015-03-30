/**
 * Created by super_000 on 3/9/2015.
 */

import java.text.NumberFormat;
import java.util.Scanner;
//import com.googlecode.charts4j.*;
import java.io.*;
import com.xeiam.xchart.Chart;
import com.xeiam.xchart.QuickChart;
import com.xeiam.xchart.SwingWrapper;
public class Driver {
    private static Scanner darkly = new Scanner(System.in);
    public static void main(String[] args) throws FileNotFoundException {

        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(5);
        boolean loop = true;
        while(loop) {
            System.out.println("HILBERT ERROR VISUALIZATION PROGRAM ULTIMATE ARCADE EDITION v0.0000001");
            System.out.println("CHOOSE THY FATE");
            System.out.println("1. Hilbert QR Visualization");
            System.out.println("2. Hilbert LU Visualization");
            System.out.println("3. Test Matrix Functions");
            System.out.println("4. Exit");


            int fate = darkly.nextInt();
            switch (fate) {
                case 1:
                    qrVisualizer();
                    break;
                case 2:
                    luVisualizer();
                    break;
                case 3:
                    matrixMenu();
                    break;
                case 4:
                    System.out.println("");
                    System.out.println("JU");
                    loop = false;
                    break;


            }
        }

    }

    public static void matrixMenu() throws FileNotFoundException{
        boolean loop = true;

            System.out.println("Enter an augmented matrix as a .dat file");

                //File augFile = new File(darkly.next());

        while(loop) {
            //Matrix
            System.out.println("What would you liked to do?");
            System.out.println("1. Find Q and R of a matrix with HouseHolders");
            System.out.println("2. Find Q and R of matrix with Givens");
            System.out.println("3. Ax = b with HouseHolders");
            System.out.println("4. Ax = b with Givens");
            System.out.println("5. Enter different matrix");
            System.out.println("6. Go back");

            switch(darkly.nextInt()) {
                case 1:
                    System.out.println("Enter a matrix as a .dat file");
                    Matrix A = DatParser.datToMatrix(new File(darkly.next()));
                    Matrix Q = Householder.getQ(A);
                    Matrix R = Householder.getR(A);
                    A.print(A.getRowDimension(), A.getColumnDimension());
                    System.out.print(" = ");
                    Q.print(Q.getRowDimension(), Q.getColumnDimension());
                    System.out.print(" * ");
                    R.print(R.getRowDimension(), R.getColumnDimension());
                    System.out.println();
                    System.out.print("Error = " + Householder.error(A));
                    String pause = darkly.next();
                    break;
                case 2:
                    System.out.println("Enter a matrix as a .dat file");
                    A = DatParser.datToMatrix(new File(darkly.next()));
                    GivensQR qrA = new GivensQR(A);
                    Q = qrA.Q;
                    R = qrA.R;
                    A.print(A.getRowDimension(), A.getColumnDimension());
                    System.out.print(" = ");
                    Q.print(Q.getRowDimension(), Q.getColumnDimension());
                    System.out.print(" * ");
                    R.print(R.getRowDimension(), R.getColumnDimension());
                    System.out.println();
                    System.out.print("Error = " + qrA.getError());
                    pause = darkly.next();
                    break;
                case 3:

                    System.out.println("Enter an augmented matrix as a .dat file");
                    File augFile = new File(darkly.next());
                    A = augSplitA(augFile);
                    Matrix b = augSplitb(augFile);
                    Matrix sol = solve_qr_b.houseSolve(A, b);
                    A.print(A.getRowDimension(), A.getColumnDimension());
                    System.out.print(" * ");
                    sol.print(sol.getRowDimension(), 0);
                    System.out.print(" = ");
                    b.print(b.getRowDimension(), 0);
                    System.out.print("Error = " + A.times(sol).minus(b).norm());
                    pause = darkly.next();
                    break;
                case 4:
                    System.out.println("Enter an augmented matrix as a .dat file");
                    augFile = new File(darkly.next());
                    A = augSplitA(augFile);
                    b = augSplitb(augFile);
                    sol = solve_qr_b.givensSolve(A, b);
                    A.print(A.getRowDimension(), A.getColumnDimension());
                    System.out.print(" * ");
                    sol.print(sol.getRowDimension(), 0);
                    System.out.print(" = ");
                    b.print(b.getRowDimension(), 0);
                    System.out.print("Error = " + A.times(sol).minus(b).norm());
                    pause = darkly.next();
                    break;
                case 5:
                    loop = false;
                    matrixMenu();
                case 6:
                    loop = false;
                    break;



            }
        }

    }

    public static Matrix augSplitA(File a) throws FileNotFoundException {
        Matrix Aug = DatParser.datToMatrix(a);
        Matrix b = new Matrix(Aug.getRowDimension(), 1);
        Matrix A = new Matrix(Aug.getRowDimension(), Aug.getColumnDimension() - 1);

        for(int row = 0; row < Aug.getRowDimension(); row++) {
            for(int col = 0; col < Aug.getColumnDimension(); col++) {
                if(col != Aug.getColumnDimension() - 1) {
                    A.set(row, col, Aug.get(row, col));
                } else {
                    b.set(row, 0, Aug.get(row, col));
                }
            }
        }

        return A;

    }

    public static Matrix augSplitb(File a) throws FileNotFoundException {
        Matrix Aug = DatParser.datToMatrix(a);
        Matrix b = new Matrix(Aug.getRowDimension(), 1);
        Matrix A = new Matrix(Aug.getRowDimension(), Aug.getColumnDimension() - 1);

        for(int row = 0; row < Aug.getRowDimension(); row++) {
            for(int col = 0; col < Aug.getColumnDimension(); col++) {
                if(col != Aug.getColumnDimension() - 1) {
                    A.set(row, col, Aug.get(row, col));
                } else {
                    b.set(row, 0, Aug.get(row, col));
                }
            }
        }

        return b;

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
        double[] x = new double[19];
        double[] qrhError = new double[19];
        double[] qrGError = new double[19];
        double[] hxError = new double[19];
        double[] gxError = new double[19];

        int i = 0;
        for(int n = 2; n <= 20; n++) {
            Matrix hilbert = createHilbert(n, n);
            GivensQR qrA = new GivensQR(hilbert);
            Matrix b = createB(n);
            Matrix xSol = solve_qr_b.houseSolve(hilbert, b);
            System.out.println("For Hilbert of size " + n + " With HouseHolders:");
            System.out.println("Xsol = ");
            xSol.print(n, 1);
            System.out.print("------");
            double qrhErr = Householder.error(hilbert);
            System.out.println("||QR - H|| = " + qrhErr);

            //Solution Error with HouseHolders
            double hxErr = (hilbert.times(xSol).minus(b)).norm();
            System.out.println("------ ||HXsol - b||" + hxErr);

            System.out.println(":)");

            //GIVENS
            System.out.println("For Hilbert of size " + n + " With Givens:");
            xSol = solve_qr_b.givensSolve(hilbert, b);
            System.out.println("Xsol = ");
            xSol.print(n, 1);
            System.out.print("------");
            double qrGErr = qrA.getError();
            System.out.println("||QR - H|| = " + qrGErr);

            //Solution Error with givens

            double gxErr = ((hilbert.times(xSol).minus(b)).norm());
            System.out.println("------ ||HXsol - b||" + gxErr);

            //Graphin stuff
            double scale = 1000000.0;
            x[i] = n;
            qrGError[i] = qrGErr*scale;
            qrhError[i] = qrhErr*scale;
            hxError[i] = hxErr*scale;
            gxError[i] = gxErr*scale;
            i++;
        }


        Chart houseChart = QuickChart.getChart("QR Error of Hilbert Matrix with HouseHolders"
                , "Hilbert size", "Error (*scaled by 10^6)", "||QR - H||", x, qrhError);
        //houseChart.getStyleManager().setYAxisLogarithmic(true);
        new SwingWrapper(houseChart).displayChart();

        Chart givensChart = QuickChart.getChart("QR Error of Hilbert Matrix with Givens"
                , "Hilbert size", "Error (*scaled by 10^6)", "||QR - H||", x, qrGError);
        new SwingWrapper(givensChart).displayChart();

        Chart hSolChart = QuickChart.getChart("Solution Error of Hilbert Matrix with HouseHolders"
                , "Hilbert size", "Error (*scaled by 10^6)", "||HXsol - b||", x, hxError);;
        new SwingWrapper(hSolChart).displayChart();

        Chart gSolChart = QuickChart.getChart("Solution Error of Hilbert Matrix with Givens"
                , "Hilbert size", "Error (*scaled by 10^6)", "||HXsol - b||", x, gxError);
        new SwingWrapper(gSolChart).displayChart();





    }

    public static void luVisualizer() {
        System.out.println("LU Factorization for Hilbert Matricies");
        System.out.println("");

        double[] x = new double[19];
        double[] luError = new double[19];
        double[] luSolError = new double[19];
        int k = 0;
        for (int n = 2; n <= 20; n++) {
            Matrix hilbert = createHilbert(n, n);
            Matrix b = createB(n);
            LU lu = new LU(hilbert);
            lu.lu_fact();
            double luErr = lu.getError();
            System.out.println("The error for a hilbert matrix of size " + n + " is " + luErr);
            Matrix c = lu.solve(b);
            System.out.println("The solution, x, for LU is ");
            for (int i = 0; i < c.getRowDimension(); i++) {
                System.out.println(c.get(i, 0));
            }
            System.out.print("The error, Hx - b, for LU is ");
            Matrix e = (hilbert.times(c).minus(b));
            double luSolErr = e.norm();
            System.out.print(luSolErr);
            System.out.println("");
            System.out.println("----------------------------------------");
            System.out.println("");
            double luErrscale = 10000000000.0;
            x[k] = n;
            luError[k] = luErr*luErrscale;
            luSolError[k] = luSolErr*luErrscale;
            k++;
        }

        Chart luChart = QuickChart.getChart("LU error of Hilbert FX"
                , "Hilbert size", "Error (*scaled by 10^6)", "||HXsol - b||", x, luError);
        new SwingWrapper(luChart).displayChart();

        Chart luSolChart = QuickChart.getChart("Solution Error of Hilbert Matrix with LU"
                , "Hilbert size", "Error (*scaled by 10^6)", "||HXsol - b||", x, luSolError);
        new SwingWrapper(luSolChart).displayChart();

    }


}
