public class Jacobi {
    Matrix a; //stands for the matrix, A
    Matrix x = null; //note that this is a vector, stands for solving the solution in the iteration
    Matrix y; //note that this is a vector, stands for the vector, b
    Matrix x0; //note that this is a vector, stands for the initial guess
    double tol;
    int maxIterations = 1000;
    int iterations = 0;

    //default constructor, assuming that matrix A and vector b are not augmented.
    public Jacobi(Matrix a, Matrix y, Matrix x0, double tol) {
        this.a = a;
        this.y = y;
        this.x0 = x0;
        this.tol = tol;
    }

    //if augmented, do other stuff.
    public Jacobi(Matrix a, Matrix x0, double tol) {
        double temp[][] = new double[a.getRowDimension()][a.getColumnDimension() - 1];
        double tempB[][] = new double[a.getRowDimension()][1];
        for (int i = 0; i < a.getRowDimension(); i++) {
            for (int j = 0; j < a.getColumnDimension(); j++) {
                if (j >= a.getColumnDimension() - 1) {
                    tempB[i][0] = a.get(i, j);
                } else {
                    temp[i][j] = a.get(i, j);
                }
            }
        }
        this.a = new Matrix(temp);
        this.y = new Matrix(tempB);
        this.x0 = x0;
        this.tol = tol;
    }
    public Matrix jacobi() {
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
                    t[i][j] = -1 * original[i][j];
                }
            }
        }
        Matrix finalS = new Matrix(s);
        Matrix finalT = new Matrix(t);

        iterations = 0;
        Matrix previousX = x0;
        boolean keepLooping = true;
        while (keepLooping) {
            if (x != null) {
                previousX = new Matrix(x.getArrayCopy());
            }
            x = ForwardSubstitution.forwardSubstitution(finalS, finalT.times(previousX).plus(y));

//            x = Multiply.multiply(diagonalInverse(finalS), finalT);
//            x = Multiply.multiply(x, previousX);
//            x = x.plus(Multiply.multiply(diagonalInverse(finalS), y));


            iterations++;
            if (iterations >= maxIterations) {
                keepLooping = false;
            }
            Matrix error = x.minus(previousX);
            if (vectorNorm(error) <= tol) {
                keepLooping = false;
            }
        }

        return x;

    }

    public double vectorNorm(Matrix a) {
        if (a.getColumnDimension() == 1 || a.getRowDimension() == 1) {
            double sum = 0;
            for (int i = 0; i < a.getRowDimension(); i++) {
                for (int j = 0; j < a.getColumnDimension(); j++) {
                    sum = sum + Math.pow(a.get(i, j), 2);
                }
            }
            return Math.pow(sum, 0.5);
        }
        System.out.println("The entered object was not a vector.");
        return -1;
    }
//    public Matrix diagonalInverse(Matrix a) {
//        double[][] temp = a.getArrayCopy();
//        int n = a.getColumnDimension();
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                if (i == j) {
//                    temp[i][j] =  1.00 / temp[i][j];
//                }
//            }
//        }
//        return new Matrix(temp);
//    }

    public int getIterations() {
        return iterations;
    }

    public int getMaxIterations() {
        return maxIterations;
    }

    public void print() {
        System.out.println("This is the Jacobi algorithm.");
        if (iterations < maxIterations) {
            System.out.println("The final estimated x value is: ");
            for (int i = 0; i < x.getRowDimension(); i++) {
                for (int j = 0; j < x.getColumnDimension(); j++) {
                    System.out.println(x.get(i, j));
                }
            }
            System.out.println("The operation completed after " + iterations + " iterations.");
        } else {
            System.out.println("The operation failed after " + iterations + " iterations.");
            System.out.println("The final estimated x value is: ");
            for (int i = 0; i < x.getRowDimension(); i++) {
                for (int j = 0; j < x.getColumnDimension(); j++) {
                    System.out.println(x.get(i, j));
                }
            }
        }
    }

    public static void main(String[] args) {
        double [][]test = new double[2][3];
        test[0][0] = 4;
        test[0][1] = 2;
        test[1][0] = 3;
        test[1][1] = 5;
        test[0][2] = 1;
        test[1][2] = 2;
        double [][]testB = new double[2][1];
        testB[0][0] = 1;
        testB[1][0] = 2;
        double [][]testGuess = new double[2][1];
        testGuess[0][0] = 0;
        testGuess[1][0] = 0;
        Matrix a = new Matrix(test);
        Matrix b = new Matrix(testB);
        Matrix c = new Matrix(testGuess);
        Jacobi testing = new Jacobi(a, c, 1.0/100000000.0);
        testing.jacobi();
    }
}
