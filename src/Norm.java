import Jama.Matrix;

/**
 * Gets the Norm of a Matrix
 *
 * @author Patrick tam
 * @version 0.1
 */
public class Norm {

	/**
	 * Gets the norm of the Matrix
	 * norm is defined as absolute value of max entry in Matrix
	 *
	 * @param a Matrix to find norm of
	 * @return norm of the Matrix
	 */
	public static double getNorm(Matrix a) {
		double max = 0;
		double[][] aMatrix = a.getArrayCopy();
		for (int i = 0; i < aMatrix.length; i++) {
			for (int j = 0; j < aMatrix[0].length; j++) {
				if (Math.abs(aMatrix[i][j]) > max) {
					max = Math.abs(aMatrix[i][j]);
				}
			}
		}
		return max;
	}
}