import com.sun.javafx.geom.transform.SingularMatrixException;

/**
 * Exceptions for Matrix calculations
 *
 * @author Patrick Tam
 * @version 0.1
 */
public class MatrixException extends RuntimeException{

    /**
     * No-arg Matrix Exception constructor
     */
    public MatrixException() {
        super("Matrix Operation failed.");
    }

    /**
     * Matrix Exception constructor
     * with message passed in
     */
    public MatrixException(String message) {
        super("Matrix Operation failed: " + message);
    }
}
