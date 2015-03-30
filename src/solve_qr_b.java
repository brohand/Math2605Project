import java.util.Vector;

public class solve_qr_b {

    public static Matrix houseSolve(Matrix A, Matrix b) {
        GivensQR qrA = new GivensQR(A);

        Matrix QTb = Householder.getQ(A).transpose().times(b);
        Matrix sol = BackwardSubstitution.backSub(Householder.getR(A), QTb);

        return sol;



    }

    public static Matrix givensSolve(Matrix A, Matrix b) {
        GivensQR qrA = new GivensQR(A);

        Matrix QTb = qrA.Q.transpose().times(b);
        Matrix sol = BackwardSubstitution.backSub(qrA.R, QTb);

        return sol;

    }

//    private Matrix solutionMatrix(Matrix A, double[] b) {
//
//    }

//    //For testing
//    public static void main(String[] args) {
//        double[][] testArr = {{4, 5},{0,-3}};
//         Matrix testMat = Driver.createHilbert(4,4);
//        testMat.print(4,4);
//        System.out.println();
//        testMat.transpose().print(4, 4);
//        //System.out.println();
//        //backSub(testMat,).print(4, 4);
//        System.out.println();
//
//        double[][] b = {{0.0464159},{0.0464159},{0.0464159},{0.0464159}};
//        Matrix QTb = Multiply.multiply(Householder.getQ(testMat).transpose(),new Matrix(b));
//        Matrix correctSol = Householder.getR(testMat).solve(QTb);
//        correctSol.print(1,4);
//        System.out.println();
//
//        Matrix shitSol = BackwardSubstitution.backSub(Householder.getR(testMat), QTb);
//        shitSol.print(1,4);
//
//
//        Matrix nuthaShitSol = houseSolve(testMat, new Matrix(b));
//        nuthaShitSol.print(1,4);
//
//
//    }

}
