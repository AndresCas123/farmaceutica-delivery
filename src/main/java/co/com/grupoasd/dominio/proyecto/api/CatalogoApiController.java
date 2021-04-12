package co.com.grupoasd.dominio.proyecto.api;

import co.com.grupoasd.dominio.proyecto.message.CatalogoRequest;
import co.com.grupoasd.dominio.proyecto.service.CatalogoService;
import co.com.grupoasd.dominio.proyecto.util.LogTrace;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@Api(value = "catalogos", description = "API para gestion de catalogos.", tags = { "catalogos" })
@RequestMapping("${app.context-api}/catalogos")
@RestController
@Slf4j
@AllArgsConstructor
public class CatalogoApiController {

    private final CatalogoService catalogoService;

    @ApiOperation(value = "Creacion de catalogos de servicio.",
            nickname = "crear",
            notes = "Creacion de catalogos de servicio.",
            tags = { "catalogos" })
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200, message = "Catalogo creado."),
            @ApiResponse(
                    code = 400, message = "Parametros invalidos.")
        }
    )
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody List<CatalogoRequest> catalogoRequest) {
        LogTrace.trace(log);
        catalogoService.crear(catalogoRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
