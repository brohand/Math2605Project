import Jama.Matrix; 
import java.util.ArrayList;

public class qr_fact_househ {
	public static Matrix x;
	public static Matrix q;
	public static Matrix test;
	public static double error;
	public static void getQR(Matrix x) {
		ArrayList<Matrix> qList = new ArrayList<Matrix>();
		test = x;
		Matrix orig = x;
		int count = 0;
		q = Identity.getIdentity((int)test.getColumnDimension());
		for (int c = 0; c < test.getColumnDimension()-1; c++) {
			Matrix i1 = Identity.getIdentity((int)test.getColumnDimension());
			Matrix i2 = Identity.getIdentity((int)test.getColumnDimension()-count);
			Matrix e1 = Identity.gete1(test.getRowDimension() - count);
			Matrix section = test.getMatrix(count,test.getRowDimension()-1,c,c);
			Matrix v = section.plus(e1.times(Norm.getNorm(section)));
			Matrix u = v.times(1./(Norm.getNorm(v)));
			Matrix h = i2.minus((Multiply.multiply(u, u.transpose())).times(2));
			for (int r1 = count; r1 < i1.getRowDimension(); r1++) {
				for (int c1 = count; c1 < i1.getColumnDimension(); c1++) {
					i1.set(r1,c1,h.get(r1-count,c1-count));
				}
			}
			qList.add(i1);
			test = Multiply.multiply(i1,test);
			count++;
		}
		for (int i = qList.size()-1; i>=0; i--) {
			q = Multiply.multiply(qList.get(i),q);
		}
		error = Norm.getNorm((Multiply.multiply(q,test).minus(orig)));
	}

	public static Matrix getQ() {
		return q;
	}
	public static Matrix getR() {
		return test;
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