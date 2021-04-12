package co.edu.javeriana.farmaceutica.delivery.exception;

/**
 * Excepcion general de tipo Runtime.
 * @author Juan Carlos Castellanos <jccastellanos@grupoasd.com.co>
 */
public class GeneralRuntimeException extends RuntimeException {
    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = -8113643211312220852L;
    
    /**
     * Constructor.
     * @param message Mensaje de la excepcion.
     */
    public GeneralRuntimeException(String message) {
        super(message);
    }

    /**
     * Constructor.
     * @param message Mensaje de la excepcion.
     * @param cause Excepcion raiz.
     */
    public GeneralRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
