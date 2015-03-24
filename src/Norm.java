import Jama.Matrix;
import java.lang.Math;
public class Norm {
	public static double getNorm(Matrix t) {
		double sum = 0;
		for (int r = 0; r < t.getRowDimension(); r++) {
			for (int c = 0; c < t.getColumnDimension(); c++) {
				sum += Math.pow(t.get(r,c),2);
			}
		}
		return Math.pow(sum, 0.5);
	}
	// public static void main(String[] args) {
	//  	double[][] x = {{1},{2}};
	//  	Matrix t = new Matrix(x);
	//  	System.out.println(getNorm(t));   
	// }
}