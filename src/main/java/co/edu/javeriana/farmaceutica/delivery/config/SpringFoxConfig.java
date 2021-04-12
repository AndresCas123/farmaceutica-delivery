package co.edu.javeriana.farmaceutica.delivery.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Configuraciones para Spring Fox Swagger.
 * @author Juan Carlos Castellanos <jccastellanos@grupoasd.com>
 */
@Configuration
@EnableSwagger2
public class SpringFoxConfig {

    /**
     * Se habilita Spring Fox.
     * @return Docket
     */
    @Bean
    public Docket api() { 
        // En modo desarrollo se expone el swagger
        return new Docket(DocumentationType.SWAGGER_2)
          .select()
          .apis(RequestHandlerSelectors.any())
          .paths(PathSelectors.any())
          .build();
    }
}
