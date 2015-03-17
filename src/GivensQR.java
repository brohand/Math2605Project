import Jama.Matrix;
import java.util.ArrayList;

/**
 * Created by super_000 on 3/17/2015.
 */
public class GivensQR {
    Matrix matrix;
    ArrayList<Matrix> Givens;
    public Matrix Q;
    public Matrix R;

    GivensQR(Matrix matrix) {
        this.matrix = matrix;
        Givens = new ArrayList<Matrix>(10);
        R = findR(this.matrix);
        Q = findQ();
    }

    public Matrix findR(Matrix matrix) {
        double r;
        double c;
        double s;

        for(int j = 0; j < matrix.getColumnDimension(); j++) {
            int pivot = j;
            for(int i = pivot + 1; i < matrix.getRowDimension(); i++) {
                double b = matrix.get(i, j);
                if(b != 0) {
                    double a = matrix.get(pivot, pivot);
                    r = Math.sqrt(b * b + a * a);
                    c = a / r;
                    s = -b / r;

                    double[][] newG = new double[matrix.getRowDimension()][matrix.getColumnDimension()];
                    for (int k = 0; k < matrix.getColumnDimension(); k++) {
                        newG[k][k] = 1.;
                    }
                    newG[pivot][pivot] = c;
                    newG[i][i] = c;
                    newG[j][i] = -s;
                    if (i > j) {
                        newG[i][j] = s;
                    }

                    Matrix newGivens = new Matrix(newG);
                    Givens.add(newGivens.transpose());

                    matrix = newGivens.arrayTimes(matrix);
                }
            }
        }
        return matrix;
    }

    public Matrix findQ() {
        Matrix Q = Givens.get(0);
        for(int i = 0; i < Givens.size(); i++) {
            if(i == 0) {
                Q = Givens.get(i);
            }
            if(i + 1 < Givens.size()) {
                Q = Q.arrayTimes(Givens.get(i + 1));
            }

        }

        return Q;
    }

}
