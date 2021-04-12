package co.com.grupoasd.dominio.proyecto.api;

import co.com.grupoasd.dominio.proyecto.entity.Proveedor;
import co.com.grupoasd.dominio.proyecto.message.ProveedorRequest;
import co.com.grupoasd.dominio.proyecto.service.ProveedorService;
import co.com.grupoasd.dominio.proyecto.util.LogTrace;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "proveedores", description = "API para gestion de proveedores.", tags = { "proveedores" })
@RequestMapping("${app.context-api}/proveedores")
@RestController
@Slf4j
@AllArgsConstructor
public class ProveedorApiController {

    private final ProveedorService proveedorService;

    @ApiOperation(value = "Creacion de proveedores.",
            nickname = "crear",
            notes = "Creacion de proveedores.",
                    tags = { "proveedores" })
    @ApiResponses(value = {
        @ApiResponse(
                code = 200, message = "Proveedor creado.",
                response = Proveedor.class),
        @ApiResponse(
                code = 400, message = "Parametros invalidos.")
        }
    )
    @PostMapping
    public ResponseEntity<?> crear(@ApiParam(required = true, value = "Proveedor a crear")
        @RequestBody ProveedorRequest proveedorRequest) {
        LogTrace.trace(log);
        return new ResponseEntity<>(proveedorService.crear(proveedorRequest), HttpStatus.OK);
    }
}