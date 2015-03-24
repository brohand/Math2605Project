import Jama.Matrix;
public class Multiply {
    public static Matrix multiply(Matrix matrix1, Matrix matrix2) {
        double [][] a = matrix1.getArray();
        double [][] b = matrix2.getArray();
        int row1 = matrix1.getRowDimension();
        int column1 = matrix1.getColumnDimension();
        int row2 = matrix2.getRowDimension();
        int column2 = matrix2.getColumnDimension();
        double [][] c = new double[row1][column2];
        for (int k = 0; k < column2; k++) {
            for (int i = 0; i < row1; i++) {
                for (int j = 0; j < column1; j++) {
                    c[i][k] = (a[i][j] * b[j][k]) + c[i][k];
                }
            }
        }
        Matrix matrix = new Matrix(c, row1, column2);
        return matrix;
    }
//}
    public static void main(String[] args) {
        double[][] g = {{2.414213562},{0},{1}};
        double[][] h = {{2.414213562,0,1}};
        Matrix x = new Matrix(g);
        Matrix matrix = new Matrix(h);
        Matrix y = Multiply.multiply(x, matrix);
        print1(y);
    }
    public static void print1(Matrix x) {
        double[][] valsTransposed = x.getArray();
        for(int i = 0; i < valsTransposed.length; i++) {
            for(int j = 0; j < valsTransposed[i].length; j++) {        
                System.out.print( " " + valsTransposed[i][j] );
            }
            System.out.println("");
        }
    }
}