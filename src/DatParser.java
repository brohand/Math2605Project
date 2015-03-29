import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Turns dat files into Matrices and Matrices into dat files
 *
 * @author Patrick Tam
 * @version 0.1
 */
public class DatParser {

    public static Matrix datToMatrix(File a) throws FileNotFoundException{
        Scanner file = new Scanner(new FileReader(a));
        return null;


    }

    public static File matrixToDat(Matrix a) {
        return null;
    }
}
