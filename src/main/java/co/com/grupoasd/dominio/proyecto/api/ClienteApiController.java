package co.com.grupoasd.dominio.proyecto.api;

import co.com.grupoasd.dominio.proyecto.entity.Cliente;
import co.com.grupoasd.dominio.proyecto.service.ClienteService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.com.grupoasd.dominio.proyecto.message.ClienteRequest;
import co.com.grupoasd.dominio.proyecto.util.LogTrace;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.ApiResponse;
import org.springframework.http.HttpStatus;

@Api(value = "clientes", description = "API para gestion de clientes.", tags = { "clientes" })
@RequestMapping("${app.context-api}/clientes")
@RestController
@Slf4j
@AllArgsConstructor
public class ClienteApiController {

    private final ClienteService clienteService;

    @ApiOperation(value = "Creacion de clientes.",
            nickname = "crear",
            notes = "Creacion de clientes.",
                    tags = { "clientes" })
    @ApiResponses(value = {
        @ApiResponse(
                code = 200, message = "Cliente creado.",
                response = Cliente.class),
        @ApiResponse(
                code = 400, message = "Parametros invalidos.")
        }
    )
    @PostMapping
    public ResponseEntity<?> crear(@ApiParam(required = true, value = "Cliente a crear")
        @RequestBody ClienteRequest clienteRequest) {
        LogTrace.trace(log);
        return new ResponseEntity<>(clienteService.crear(clienteRequest), HttpStatus.OK);
    }
}