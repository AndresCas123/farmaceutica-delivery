package co.edu.javeriana.farmaceutica.delivery.util;

import java.util.UUID;

/**
 * Clase utilitaria para generacion de identificadores aleatorios.
 * @author Juan Carlos Castellanos <jccastellanos@grupoasd.com.co>
 */
public final class IdUtil {
    
    /**
     * Constructor privado. Esta clase no puede ser instanceada.
     */
    private IdUtil() {
        
    }

    /**
     * Genera un identificador aleatorio de 32 caracteres.
     * @return Cadena con el identificador.
     */
    public static String generate() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
