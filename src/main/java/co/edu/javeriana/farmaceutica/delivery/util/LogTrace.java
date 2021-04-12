package co.edu.javeriana.farmaceutica.delivery.util;

import org.slf4j.Logger;

/**
 * Clase utilitaria para manejo de logs del sistema.
 * @author Juan Carlos Castellanos <jccastellanos@grupoasd.com.co>
 */
public final class LogTrace {
    
    /**
     * Esta clase es utilitaria y no puede ser instanceada.
     */
    private LogTrace() {
        
    }
    /**
     * Deja una traza del metodo desde donde se invoca para hacer debug de codigo.
     * @param logger Loggger para realizar la traza.
     */
    public static void trace(Logger logger) {
        logger.debug(Thread.currentThread().getStackTrace()[2].getMethodName());
    }
}
