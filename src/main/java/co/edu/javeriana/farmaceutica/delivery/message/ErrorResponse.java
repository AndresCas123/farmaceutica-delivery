package co.edu.javeriana.farmaceutica.delivery.message;

import lombok.Data;

/**
 * Mensaje generico de error del servidor.
 * @author Juan Carlos Castellanos <jccastellanos@grupoasd.com.co>
 */
@Data
public class ErrorResponse {
    /**
     * Codigo de error.
     */
    private String errorcode;
    /**
     * Timestamp del error.
     */
    private String timestamp;
    /**
     * Mensaje de error.
     */
    private String message;
}
