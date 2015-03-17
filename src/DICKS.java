/**
 * Created by super_000 on 3/9/2015.
 */
import Jama.*;
import java.text.NumberFormat;
public class DICKS {
    public static void main(String[] args) {
        NumberFormat nf = NumberFormat.getInstance();
        System.out.println(67);
        double[][] array = {{1.,2.,3},{4.,5.,6.},{7.,8.,10.}};
        Matrix A = new Matrix(array);
        Matrix b = Matrix.random(3,1);
        Matrix x = A.solve(b);
        A.print(nf, 3);
        System.out.print(" * ");
        x.print(nf, 1);
        System.out.print(" = ");
        b.print(nf, 1);

    }


}
