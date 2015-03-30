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
            leslie.print(3,3);

            System.out.println("Leslie Matrix:");
            leslie.getAugmentedMatrix().print(3,3);

            System.out.println("Population Vector");
            System.out.println(leslie.getAugmentedVector().toString() + "\n");

            runSimulation(leslie.getAugmentedMatrix(), leslie.getAugmentedVector(), iterations);

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
        System.out.println("Iteration: 0 Vector: " + xk);
        for (int i = 1; i <= iterations; i++) {
            xk = a.times(xk);
            System.out.println("Iteration: " + i + " Vector: " + xk);
        }
    }
}
