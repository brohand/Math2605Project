import java.util.ArrayList;

/**
 * Created by super_000 on 3/17/2015.
 */
public class GivensQR {
    Matrix matrix;
    ArrayList<Matrix> Givens;
    public Matrix Q;
    public Matrix R;
    public static double error;

    GivensQR(Matrix matrix) {
        this.matrix = matrix;
        Givens = new ArrayList<Matrix>(10);
        R = findR(this.matrix);
        Q = findQ();
        this.error = findError();
    }



    public Matrix findR(Matrix mat) {
        double r;
        double c;
        double s;

        for(int j = 0; j < mat.getColumnDimension(); j++) {
            int pivot = j;
            for(int i = pivot + 1; i < mat.getRowDimension(); i++) {
                double b = mat.get(i, j);
                    double a = mat.get(pivot, pivot);
                    r = Math.sqrt(b * b + a * a);
                    c = a / r;
                    s = (-1. * b) / r;

                    double[][] newG = new double[mat.getRowDimension()][mat.getColumnDimension()];
                    for (int k = 0; k < mat.getColumnDimension(); k++) {
                        newG[k][k] = 1.;
                    }
                    newG[pivot][pivot] = c;
                    newG[i][i] = c;
                    newG[j][i] = -1. * s;
                    if (i > j) {
                        newG[i][j] = s;
                    } else {
                        newG[j][i] *= -1;
                        newG[i][j] = -1 * s;
                    }

                    Matrix newGivens = new Matrix(newG);
                    Givens.add(newGivens.transpose());

                    mat = newGivens.times(mat);

            }
        }
        return mat;
    }

    public Matrix findQ() {
        Matrix Q = Givens.get(0);
        for(int i = 0; i < Givens.size(); i++) {
            if(i == 0) {
                Q = Givens.get(i);
            }
            if(i + 1 < Givens.size()) {
                Q = Q.times(Givens.get(i + 1));
            }

        }

        return Q;
    }
    public double findError() {
        error = Q.times(R).minus(matrix).norm();
        return error;
    }

    public double getError() {
        return this.error;
    }



}
