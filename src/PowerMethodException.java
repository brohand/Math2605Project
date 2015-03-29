/**
 * Power Method Exception
 * thrown when it does not converge
 *
 * @author Patrick Tam
 * @version 0.1
 */
public class PowerMethodException extends RuntimeException{

    public PowerMethodException() {
        super("The PowerMethod failed");
    }
    public PowerMethodException(String message) {
        super("Power Method failed:" + message);
    }
}
