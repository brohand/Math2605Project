import Jama.Matrix;
public class ForwardSubstitution {

    //Please note that this ONLY works on matrices that are
    //lower triangular (all values on or BELOW the diagonal)
    public static Matrix forwardSubstitution(Matrix matrixA, Matrix matrixB) {
        double [][] a = matrixA.getArrayCopy();
        double[][] b = matrixB.getArrayCopy();
        double[][] x = new double[matrixA.getRowDimension()][1];
        for (int j = 0; j < matrixA.getRowDimension(); j++) {
            for (int k = 0; k < matrixA.getColumnDimension(); k++) {
                if (k == 0 && j == 0) {
                    x[j][0] = b[j][0] / a[j][k];
                } else {
                    if (k == j) {
                        double temp = 0;
                        for (int l = k - 1; l >= 0; l--) {
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
                {4, 0, 0, 0},
                {3, 5, 0, 0},
                {2, 6, 8, 0},
                {1, 5, 3, 6}
        };
        double [][]testB = {
                {1},
                {2},
                {5},
                {8}
        };
        ForwardSubstitution.forwardSubstitution(new Matrix(test), new Matrix(testB));
    }
}
