import Jama.Matrix;
import java.lang.Math;
public class Identity {
	public static Matrix getIdentity(int r) {
		Matrix x = new Matrix(r,r);
		for (int y = 0; y < r; y++) {
			x.set(y,y,1);			
		}
		return x;
	}
	public static Matrix gete1(int r) {
		Matrix x = new Matrix(r,1);
		x.set(0,0,1);
		return x;
	}
	// public static void main(String[] args) {
	// 	Matrix test = Identity.getIdentity(10);
	// 	System.out.println(test.get(0,0));
	// 	System.out.println(test.get(1,0));
	// 	System.out.println(test.get(9,9));
	// }
}