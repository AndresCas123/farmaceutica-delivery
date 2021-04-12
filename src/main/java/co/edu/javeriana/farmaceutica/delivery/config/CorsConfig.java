package co.edu.javeriana.farmaceutica.delivery.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuracion de CORS.
 * @author Juan Carlos Castellanos <jccastellanos@grupoasd.com.co>
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    
    /**
     * Permite habilitar el CORS desde cualquier origen en enterno de desarrollo. 
     * En entorno productivo el CORS debe ser manejado por el API Gateway.
     * @param registry CorsRegistry.
     * @author Juan Carlos Castellanos <jccastellanos@grupoasd.com.co>
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }
    
}
