import Jama.Matrix;

public class BackwardSubstitution {

    //Please note that this ONLY works on matrices that are
    //upper triangular (all values on or BELOW the diagonal)
    public static Matrix backSub(Matrix B, Matrix b) {
        Matrix R = B;
        //Matrix I = Identity.getIdentity(R.getColumnDimension());
        double[][] resultArr = new double[B.getColumnDimension()][1];
        Matrix result = new Matrix(resultArr);
        //double lastX = b.get(b.getRowDimension() - 1, 0) / B.get(B.getRowDimension() - 1, B.getColumnDimension() - 1);
        //resultArr[b.getRowDimension() - 1][0] = lastX;

        for(int row = B.getRowDimension() - 1; row >= 0; row--) {
            double currPiv = B.get(row, row);
            double x = b.get(row, 0) / currPiv;
            for(int i = 0; i < B.getColumnDimension(); i++) {
                double mod = 1;
                if(i != row) {
                    if(i != 0 && i < b.getRowDimension()) {
                        mod = result.get(i, 0);
                    }
                    x -= mod*B.get(row, i) / currPiv;
                }
            }
            result.set(row, 0, x);

        }

//        for (int i = 0; i < result.getRowDimension(); i++) {
//            for (int j = 0; j < result.getColumnDimension(); j++) {
//                System.out.println(result.get(i, j));
//            }
//        }
        return result;

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
        BackwardSubstitution.backSub(new Matrix(test), new Matrix(testB));
    }

}
