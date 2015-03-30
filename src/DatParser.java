import java.io.*;
import java.util.*;

/**
 * Turns dat files into Matrices and Matrices into dat files
 *
 * @author Patrick Tam
 * @version 0.1
 */
public class DatParser {

    public static void main(String[] args) {
        try {
            datToMatrix(new File("stuff.dat")).print(4,4);
            datToMatrix(new File("stuff2.dat")).print(4,4);
            datToMatrix(new File("stuff3.dat")).print(4,4);
        } catch(Exception e) {
            System.out.println("fuck you");
        }
    }

    /**
     * Converts a file to a Matrix
     *
     * @param a path to file (relative to project root)
     * @return a Matrix
     * @throws FileNotFoundException
     */
    public static Matrix datToMatrix(File a) throws FileNotFoundException{
        LinkedList<String> lines = new LinkedList<>();
        Scanner file = new Scanner(a);
        while (file.hasNextLine()) {
            lines.add(file.nextLine());
        }
        double[][] m = new double[lines.size()][];
        for(int i = 0; i < m.length; i++) {
            String b = lines.remove().trim();
            String[] line;
            if (b.contains(" ")) {
                line = b.split("\\s+");
            } else if (b.contains(",")){
                line = b.split(",");
            } else {
                throw new IllegalArgumentException("Invalid Matrix format. Separate colummns with a space, and rows with a new line.");
            }
            double[] row = new double[line.length];
            for(int j = 0; j < row.length; j++) {
                row[j] = Double.parseDouble(line[j]);
            }
            m[i] = row;
        }
        return new Matrix(m);
    }

    /**
     * Converts a Matrix to a file
     *
     * @param a Matrix
     * @param filename path to file (relative to project root)
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException
     */
    public static void matrixToDat(Matrix a, String filename) throws FileNotFoundException, UnsupportedEncodingException {
        matrixToDat(a, filename, "UTF-8");
    }

    /**
     * Converts a Matrix to a file
     *
     * @param a Matrix
     * @param filename path to file (relative to project root)
     * @param encoding encoding of file
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException
     */
    private static void matrixToDat(Matrix a, String filename, String encoding) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter file = new PrintWriter(filename, encoding);
        double[][] m = a.getArray();
        for (int i = 0; i < m.length; i++) {
            String line = "";
            for (int j = 0; j < m[0].length; j++) {
                line += m[i][j] + " ";
            }
            file.println(line);
        }
        file.close();
    }
}
