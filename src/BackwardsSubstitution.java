//Jinsong's version of backwards substitution, created because SOMEONE was taking too long

import Jama.Matrix;
public class BackwardsSubstitution {

    //Please note that this ONLY works on matrices that are
    //upper triangular (all values on or BELOW the diagonal)
    public static Matrix backwardsSubstitution(Matrix matrixA, Matrix matrixB) {
        double [][] a = matrixA.getArrayCopy();
        double[][] b = matrixB.getArrayCopy();
        double[][] x = new double[matrixA.getRowDimension()][1];
        for (int j = matrixA.getRowDimension() - 1; j >= 0; j--) {
            for (int k = matrixA.getColumnDimension() - 1; k >= 0; k--) {
                if (k == matrixA.getColumnDimension() - 1 && j == matrixA.getRowDimension() - 1) {
                    x[j][0] = b[j][0] / a[j][k];
                } else {
                    if (k == j) {
                        double temp = 0;
                        for (int l = k + 1; l < matrixA.getColumnDimension(); l++) {
                            temp = temp + (x[l][0] * a[j][l]);
                        }
                        x[j][0] = (b[k][0] - temp) / a[j][k];
                    }
                }
            }
        }
        Matrix finalA = new Matrix(a);
        Matrix finalX = new Matrix(x);
//        for (int i = 0; i < finalX.getRowDimension(); i++) {
//            for (int j = 0; j < finalX.getColumnDimension(); j++) {
//                System.out.println(finalX.get(i, j));
//            }
//        }
        return finalX;
    }

    public static void main(String[] args) {
        double [][]test = {
                {1, 5, 3, 6},
                {0,3,5,7},
                {0,0,2,8},
                {0,0,0,4},
        };
        double [][]testB = {
                {1},
                {2},
                {5},
                {8}
        };
        BackwardsSubstitution.backwardsSubstitution(new Matrix(test), new Matrix(testB));
    }
}
