import Jama.Matrix;

import java.util.Vector;

public class solve_qr_b {

    public static Matrix Solve(Matrix A, Matrix b) {
        GivensQR qrA = new GivensQR(A);

        Matrix QTb = Multiply.multiply(qrA.Q.transpose(),b);
        Matrix rInverse = backSub(qrA.R);

        return Multiply.multiply(rInverse,QTb);



    }

//    private Matrix solutionMatrix(Matrix A, double[] b) {
//
//    }

    //For testing
    public static void main(String[] args) {
        double[][] testArr = {{-1,0},{0,1}};
         Matrix testMat = Driver.createHilbert(4,4);
        testMat.print(2,2);
        System.out.println();

        double[][] b = {{0.0464159},{0.0464159},{0.0464159},{0.0464159}};

        Matrix correctSol = testMat.solve(new Matrix(b));
        correctSol.print(1,4);
        System.out.println();

        Matrix shitSol = Solve(testMat, new Matrix(b));
        shitSol.print(1,4);


    }
    public static Matrix backSub(Matrix B) {
        Matrix R = B;
        Matrix I = Identity.getIdentity(R.getColumnDimension());
        for(int col = 0; col < R.getColumnDimension(); col++) {
                int row = col;
                double currPiv = R.get(row, col);
                if(currPiv != 1) {
                    for (int i = 0; i < R.getColumnDimension(); i++) {
                        double newRVal = R.get(row, i) / currPiv;
                        double newIVal = I.get(row, i) / currPiv;
                        R.set(row, i, newRVal);
                        I.set(row, i, newIVal);
                    }
                }

                for(int i = row + 1; i < R.getRowDimension(); i++) {
                    double decVal = (R.get(i, col) < 0) ? R.get(i, col) : -1 * R.get(i, col);
                    for(int j = 0; j < R.getColumnDimension(); j++) {
                        R.set(i,j, R.get(i, j) + decVal*R.get(i-1, j));
                        I.set(i, j, I.get(i, j) + decVal*R.get(i - 1, j));
                    }
                }




        }

        for(int col = R.getColumnDimension() - 1; col >= 0; col--) {
                int row = col;
                for(int i = row - 1; i >= 0; i--) {
                    double decVal = (R.get(i, col) < 0) ? R.get(i, col) : -1 * R.get(i, col);
                    for(int j = 0; j < R.getColumnDimension(); j++) {
                        R.set(i,j, R.get(i, j) + decVal*R.get(i+1, j));
                        I.set(i, j, I.get(i, j) + decVal*R.get(i+1, j));
                    }
                }

        }

        return I;
    }



}
