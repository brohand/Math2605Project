/**
 * Created by super_000 on 3/9/2015.
 */
import Jama.*;
import java.text.NumberFormat;
public class Driver {
    public static void main(String[] args) {
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(5);



        Matrix Hilbert = createHilbert(4, 4);
        Hilbert.print(nf, 4);

        GivensQR givensQR = new GivensQR(Hilbert);

        givensQR.Q.print(nf, 4);
        givensQR.R.print(nf, 4);
        //Matrix A = givensQR.Q.times(givensQR.R);
        //A.print(nf, 4);


        //LUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU
//        System.out.println("LU Factorization for Hilbert Matricies");
//        System.out.println("");
//        for (int n = 2; n <= 20; n++) {
//            Matrix hilbert = createHilbert(n, n);
//            Matrix b = createB(n);
//            LU lu = new LU(hilbert);
//            lu.lu_fact();
//            System.out.println("The error for a hilbert matrix of size " + n + " is " + lu.getError());
//            Matrix c = lu.solve(b);
//            System.out.print("The solution, x, for LU is ");
//            c.print(c.getRowDimension(), c.getColumnDimension());
//            System.out.println("");
//            System.out.print("The error, Hx - b, for LU is ");
//            Matrix e = Multiply.multiply(hilbert, c).minus(b);
//            System.out.print(Norm.getNorm(e));
//        }
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
            b[i][0] = Math.pow(0.1, size/3);
        }
        return new Matrix(b);
    }


}
