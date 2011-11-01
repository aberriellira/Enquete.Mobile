package enquete.exception;

import java.lang.ArrayStoreException;

/**
 *
 * @author Anselmo
 */
public class StringLengthException extends ArrayStoreException {
    
    public StringLengthException()
    {
        super("O valor inserido excede o tamanho do campo.");
    }
    
    public StringLengthException(String message)
    {
        super(message);
    }
    
}
