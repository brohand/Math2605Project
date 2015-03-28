import Jama.*;
public class Jacobi {
    Matrix a;
    Matrix x; //note that this is a vector
    Matrix y; //note that this is a vector
    Matrix x0; //note that this is a vector
    double tol;

    public Jacobi(Matrix a, Matrix x, Matrix y, Matrix x0, double tol) {
        this.a = a;
        this.x = x;
        this.y = y;
        this.x0 = x0;
        this.tol = tol;
    }

    public void iterate() {
        int n = a.getColumnDimension();
        double[][] original = a.getArrayCopy();
        double[][] s = new double[n][n];
        double[][] t = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    s[i][j] = original[i][j];
                    t[i][j] = 0;
                } else {
                    s[i][j] = 0;
                    t[i][j] = original[i][j];
                }
            }
        }
        Matrix finalS = new Matrix(s);
        Matrix finalT = new Matrix(t);
        finalS.print(n, n);
        finalT.print(n, n);
    }

    public static void main(String[] args) {
        double [][]test = new double[4][4];
        test[0][0] = 1;
        test[0][1] = 0.5;
        test[0][2] = 0.333333;
        test[0][3] = 0.25;
        test[1][0] = 0.5;
        test[1][1] = 0.333333;
        test[1][2] = 0.25;
        test[1][3] = 0.2;
        test[2][0] = 0.333333;
        test[2][1] = 0.25;
        test[2][2] = 0.2;
        test[2][3] = 0.166667;
        test[3][0] = 0.25;
        test[3][1] = 0.2;
        test[3][2] = 0.166667;
        test[3][3] = 0.142857;
        Matrix a = new Matrix(test);
        Jacobi testing = new Jacobi(a, a, a, a, 5);
        testing.iterate();

    }
}
