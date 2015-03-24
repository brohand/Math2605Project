/**
 * Created by super_000 on 3/9/2015.
 */
import Jama.*;
import java.text.NumberFormat;
public class Driver {
    public static void main(String[] args) {
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(5);
        System.out.println(67);
        double[][] hilbert = createHilbert(4, 4);

        Matrix Hilbert = new Matrix(hilbert);
        Hilbert.print(nf, 4);

        GivensQR givensQR = new GivensQR(Hilbert);

        givensQR.Q.print(nf, 4);
        givensQR.R.print(nf, 4);
        Matrix A = givensQR.Q.times(givensQR.R);
        A.print(nf, 4);


    }

    public static double[][] createHilbert(int rows, int columns) {
        double[][] hilbert = new double[rows][columns];
        for(int i = 0; i < rows; i++) {
            for (int j =0; j < columns; j++) {
                hilbert[i][j] = 1. / (i + j + 1.);
            }
        }
        return hilbert;
    }


}
