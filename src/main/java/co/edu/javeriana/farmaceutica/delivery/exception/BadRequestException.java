package co.edu.javeriana.farmaceutica.delivery.exception;

/**
 * Excepcion general de tipo Runtime.
 * @author Juan Carlos Castellanos <jccastellanos@grupoasd.com.co>
 */
public class BadRequestException extends RuntimeException {
    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = -8113643211312220951L;

    /**
     * Constructor.
     * @param message Mensaje de la excepcion.
     */
    public BadRequestException(String message) {
        super(message);
    }

    /**
     * Constructor.
     * @param message Mensaje de la excepcion.
     * @param cause Excepcion raiz.
     */
    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
