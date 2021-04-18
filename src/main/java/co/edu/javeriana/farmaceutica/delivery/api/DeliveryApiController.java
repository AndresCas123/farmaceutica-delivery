package co.edu.javeriana.farmaceutica.delivery.api;

import co.edu.javeriana.farmaceutica.delivery.entity.Delivery;
import co.edu.javeriana.farmaceutica.delivery.entity.Quotation;
import co.edu.javeriana.farmaceutica.delivery.message.CreateDeliveryRequest;
import co.edu.javeriana.farmaceutica.delivery.message.QuotationRequest;
import co.edu.javeriana.farmaceutica.delivery.service.DeliveryService;
import co.edu.javeriana.farmaceutica.delivery.util.LogTrace;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "deliveries", description = "API para gestión de despachos.", tags = { "deliveries" })
@RequestMapping("${app.context-api}/deliveries")
@RestController
@Slf4j
@AllArgsConstructor
public class DeliveryApiController {

    private final DeliveryService deliveryService;

    @ApiOperation(value = "Creación de despachos.",
            nickname = "create",
            notes = "Permite la creación de un despacho por parte de un cliente.",
            tags = { "deliveries" })
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200, message = "Despacho creado.",
                    response = Delivery.class),
            @ApiResponse(
                    code = 400, message = "Parámetros inválidos.")
        }
    )
    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateDeliveryRequest createDeliveryRequest) {
        LogTrace.trace(log);
        return new ResponseEntity<>(deliveryService.create(createDeliveryRequest), HttpStatus.OK);
    }

    @ApiOperation(value = "Lista todos los despachos.",
            nickname = "list",
            notes = "Permite listar los despachos disponibles. Se puede filtrar por el identificador del cliente y por el estado " +
                    "del despacho. Si no se envía filtro retorna todos los despachos.",
            tags = { "deliveries" })
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200, message = "Listado con los despachos. Si no existen retorna una lista vacía.",
                    response = Delivery.class, responseContainer = "List")
        }
    )
    @GetMapping
    public ResponseEntity<?> list(@ApiParam(value = "Identificador del cliente", required = false) @RequestParam(required = false) String client,
                                  @ApiParam(value = "Estado del despacho", required = false) @RequestParam(required = false) String state) {
        LogTrace.trace(log);
        return new ResponseEntity<>(deliveryService.list(client, state), HttpStatus.OK);
    }

    @ApiOperation(value = "Retorna un despacho.",
            nickname = "list",
            notes = "Permite retornar un despacho de acuerdo a su identificador.",
            tags = { "deliveries" })
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200, message = "Retorna el despacho solicitado.", response = Delivery.class),
            @ApiResponse(
                    code = 404, message = "Si el despacho no existe.")
        }
    )
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> get(@ApiParam(value = "Identificador del despacho", required = true) @PathVariable String id) {
        LogTrace.trace(log);
        return deliveryService.get(id)
                .map(delivery -> new ResponseEntity<>(delivery, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @ApiOperation(value = "Creación de cotización.",
            nickname = "quotesCreate",
            notes = "Permite la creación de una cotización por parte de un proveedor. Solo se permite una cotización " +
                    "por cada proveedor por lo cual si ya existe una cotización realizada por un proveedor esta es actualizada.",
            tags = { "deliveries" })
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200, message = "Cotización creada.",
                    response = Quotation.class),
            @ApiResponse(
                    code = 400, message = "Parámetros inválidos.")
        }
    )
    @PostMapping(value = "/{id}/quotes")
    public ResponseEntity<?> quotesCreate(@ApiParam(value = "Identificador del despacho", required = true)
                                              @PathVariable String id, @RequestBody QuotationRequest quotationRequest) {
        LogTrace.trace(log);
        return new ResponseEntity<>(deliveryService.quotationCreate(id, quotationRequest), HttpStatus.OK);
    }

    @ApiOperation(value = "Listar cotizaciones de un despacho.",
            nickname = "quotesList",
            notes = "Permite listar todas las cotizaciones de un despacho.",
            tags = { "deliveries" })
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200, message = "Listado con las cotizaciones. Si no existen retorna una lista vacía.",
                    response = Quotation.class, responseContainer = "List")
        }
    )
    @GetMapping(value = "/{id}/quotes")
    public ResponseEntity<?> quotesList(@ApiParam(value = "Identificador del despacho", required = true)
                                            @PathVariable String id) {
        LogTrace.trace(log);
        return new ResponseEntity<>(deliveryService.quotes(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Seleccionar cotización ganador.",
            nickname = "quotesWinner",
            notes = "Permite seleccionar la cotización ganadora para un despacho.",
            tags = { "deliveries" })
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200, message = "Cotización ganadora.",
                    response = Quotation.class),
            @ApiResponse(
                    code = 400, message = "Parámetros inválidos.")
        }
    )
    @PostMapping(value = "/{id}/quotes/{quotationId}/winner")
    public ResponseEntity<?> quotesWinner(@ApiParam(value = "Identificador del despacho", required = true) @PathVariable String id,
                                          @ApiParam(value = "Identificador de la cotización", required = true) @PathVariable String quotationId) {
        LogTrace.trace(log);
        return new ResponseEntity<>(deliveryService.quotationWinner(quotationId), HttpStatus.OK);
    }
}
