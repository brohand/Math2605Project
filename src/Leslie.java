import java.io.File;
import java.io.FileNotFoundException;

/**
 * Calculates a Leslie Matrix
 *
 * @author Patrick Tam
 * @version 0.1
 */
public class Leslie {

    /**
     * Runs a Leslie matrix to simulate population
     *
     * @param args filename(of an augmented matrix), iterations
     */
    public static void main(String[] args) {
        try {
            Matrix leslie = DatParser.datToMatrix(new File(args[0]));
            int iterations = Integer.parseInt(args[1]);

            System.out.println("Augmented Leslie Matrix:");
            leslie.print(3, 3);

            System.out.println("Leslie Matrix:");
            leslie.getAugmentedMatrix().print(3, 3);

            System.out.println("Population Vector");
            System.out.println(leslie.getAugmentedVector().toString() + "\n");

            runSimulation(leslie.getAugmentedMatrix(), leslie.getAugmentedVector(), iterations);
            //System.out.println(asString(leslie.getAugmentedMatrix()));

        } catch (FileNotFoundException e) {
            System.out.println("File Not Found: " + e.getMessage());
        } catch(Exception e) {
            System.out.println("Whoops! " + e.getMessage());
        }
    }

    /**
     * Runs a Leslie population simulation
     *
     * @param a Leslie Matrix
     * @param xk initial population vector
     * @param iterations number of iterations
     */
    public static void runSimulation(Matrix a, Vector xk, int iterations) {
        System.out.println("Iteration: 0 Vector: " + xk + " Sum: " + xk.sum());
        for (int j = 0; j < xk.length(); j++) {
           System.out.println(xk.get(j));
        }
        for (int i = 1; i <= iterations; i++) {
            xk = a.times(xk);
            System.out.println("Iteration: " + i + " Vector: " + xk + " Sum: " + xk.sum());
            //System.out.println(xk.sum());
            for (int j = 0; j < xk.length(); j++) {
               System.out.println(xk.get(j));
            }
        }
    }

    public static String asString(Matrix a) {
        String output = "";
        output +="{";
        for (int i = 0; i < a.getRowDimension() - 1; i++) {
            output += "{";
            for (int j = 0; j < a.getColumnDimension() - 1; j++) {
                output += a.get(i,j) + ",";
            }
            output += a.get(i,a.getColumnDimension() - 1);
            output += "},";
        }
        output += "{";
        for (int j = 0; j < a.getColumnDimension() - 1; j++) {
            output += a.get(a.getRowDimension() - 1,j) + ",";
        }
        output += a.get(a.getRowDimension() - 1,a.getColumnDimension() - 1);
        output += "}";
        output += "}";
        return output;
    }
}
