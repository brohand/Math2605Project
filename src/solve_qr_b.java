import java.util.Vector;

public class solve_qr_b {

    public static Matrix houseSolve(Matrix A, Matrix b) {
        GivensQR qrA = new GivensQR(A);

        Matrix QTb = Householder.getQ(A).transpose().times(b);
        Matrix sol = BackwardSubstitution.backSub(Householder.getR(A), QTb);

        return sol;



    }
    public static Matrix houseSolve(Matrix Aug) {
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

        return houseSolve(A, b);


    }

    public static Matrix givensSolve(Matrix A, Matrix b) {
        GivensQR qrA = new GivensQR(A);

        Matrix QTb = qrA.Q.transpose().times(b);
        Matrix sol = BackwardSubstitution.backSub(qrA.R, QTb);

        return sol;

    }

    public static Matrix givensSolve(Matrix Aug) {
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

        return givensSolve(A, b);
    }



}
