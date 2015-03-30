import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Turns dat files into Matrices and Matrices into dat files
 *
 * @author Patrick Tam
 * @version 0.1
 */
public class DatParser {

    /**
     * Converts a file to a Matrix
     *
     * @param a path to file (relative to project root)
     * @return a Matrix
     * @throws FileNotFoundException
     */
    public static Matrix datToMatrix(File a) throws FileNotFoundException{
        List lines = new ArrayList();
        Scanner file = new Scanner(a);
        while (file.hasNextLine()) {
            lines.add(file.nextLine());
        }
        double[][] m = new double[lines.size()][];
        for(int i = 0; i < m.length; i++) {
            String line = (String)lines.get(i);
            Scanner lineScanner = new Scanner(line);
            List<Double> columns = new ArrayList();
            while(lineScanner.hasNextDouble()) {
                columns.add(lineScanner.nextDouble());
            }
            double[] row = new double[columns.size()];
            for (int j = 0; j < row.length; j++) {
                row[j] = columns.get(j);
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
