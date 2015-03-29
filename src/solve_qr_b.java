import Jama.Matrix;

import java.util.Vector;

public class solve_qr_b {

    public static Matrix Solve(Matrix A, Matrix b) {
        GivensQR qrA = new GivensQR(A);

        Matrix QTb = Multiply.multiply(Householder.getQ(A).transpose(),b);
        Matrix rInverse = BackwardSubstitution.backSub(Householder.getR(A), QTb);

        return rInverse;



    }

//    private Matrix solutionMatrix(Matrix A, double[] b) {
//
//    }

    //For testing
    public static void main(String[] args) {
        double[][] testArr = {{4, 5},{0,-3}};
         Matrix testMat = Driver.createHilbert(4,4);
        testMat.print(4,4);
        System.out.println();
        //testMat.inverse().print(4, 4);
        //System.out.println();
        //backSub(testMat,).print(4, 4);
        System.out.println();

        double[][] b = {{0.0464159},{0.0464159},{0.0464159},{0.0464159}};

        Matrix correctSol = testMat.solve(new Matrix(b));
        correctSol.print(1,4);
        System.out.println();

        Matrix shitSol = BackwardSubstitution.backSub(testMat, new Matrix(b));
        shitSol.print(1,4);


    }

}
