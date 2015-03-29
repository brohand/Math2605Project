/**
 * Created by super_000 on 3/9/2015.
 */

import Jama.*;
import java.text.NumberFormat;
import java.util.Scanner;
import com.googlecode.charts4j.*;
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
        double[] x = new double[19];
        double[] qrhError = new double[19];
        double[] qrGError = new double[19];
        double[] hxError = new double[19];

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
            double hxErr = Norm.getNorm(Multiply.multiply(hilbert, xSol).minus(b));
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
            hxErr = Norm.getNorm(Multiply.multiply(hilbert, xSol).minus(b));
            System.out.println("------ ||HXsol - b||" + hxErr);
            x[i] = n;
            qrhError[i] = qrhErr;
            hxError[i] = hxErr;
            i++;
        }
        DataUtil.scale(x);
        Data X = new Data(x);
        DataUtil.scale(qrhError);
        Data qrhY = new Data(qrhError);
        Data qrGY = new Data(qrGError);
        Data hxY = new Data(hxError);

        XYLine houseHolderLine = Plots.newXYLine(X, qrhY, Color.BLUE);
        ScatterPlot houseChart = GCharts.newScatterPlot(houseHolderLine);
        houseChart.setSize(400, 400);
        System.out.println(houseChart.toURLString());
        XYLine givensLine = Plots.newXYLine(X, qrGY, Color.RED);
        XYLineChart givensChart = GCharts.newXYLineChart(givensLine);
        givensChart.setSize(400, 400);
        System.out.println(givensChart.toURLString());
        XYLine solErrorLine = Plots.newXYLine(X, hxY, Color.GREEN);
        XYLineChart solErrorChart = GCharts.newXYLineChart(solErrorLine);
        solErrorChart.setSize(400, 400);
        System.out.println(solErrorChart.toURLString());



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
            System.out.println("The solution, x, for LU is ");
            for (int i = 0; i < c.getRowDimension(); i++) {
                System.out.println(c.get(i, 0));
            }
            System.out.print("The error, Hx - b, for LU is ");
            Matrix e = Multiply.multiply(hilbert, c).minus(b);
            System.out.print(Norm.getNorm(e));
            System.out.println("");
            System.out.println("----------------------------------------");
            System.out.println("");
        }
    }


}
