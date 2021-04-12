package co.com.grupoasd.dominio.proyecto.api;

import co.com.grupoasd.dominio.proyecto.entity.Delivery;
import co.com.grupoasd.dominio.proyecto.message.CreateDeliveryRequest;
import co.com.grupoasd.dominio.proyecto.message.QuotationRequest;
import co.com.grupoasd.dominio.proyecto.service.DeliveryService;
import co.com.grupoasd.dominio.proyecto.util.LogTrace;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "deliveries", description = "API para gestion de despachos.", tags = { "deliveries" })
@RequestMapping("${app.context-api}/deliveries")
@RestController
@Slf4j
@AllArgsConstructor
public class DeliveryApiController {

    private final DeliveryService deliveryService;

    @ApiOperation(value = "Creacion de despachos.",
            nickname = "create",
            notes = "Creacion de despachos.",
            tags = { "despachos" })
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200, message = "Despacho creado.",
                    response = Delivery.class),
            @ApiResponse(
                    code = 400, message = "Parametros invalidos.")
        }
    )
    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateDeliveryRequest createDeliveryRequest) {
        LogTrace.trace(log);
        return new ResponseEntity<>(deliveryService.create(createDeliveryRequest), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> list() {
        LogTrace.trace(log);
        return new ResponseEntity<>(deliveryService.list(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> get(@PathVariable String id) {
        LogTrace.trace(log);
        return deliveryService.get(id)
                .map(delivery -> new ResponseEntity<>(delivery, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/{id}/quotes")
    public ResponseEntity<?> quotesCreate(@PathVariable String id, @RequestBody QuotationRequest quotationRequest) {
        LogTrace.trace(log);
        return new ResponseEntity<>(deliveryService.quotationCreate(id, quotationRequest), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/quotes")
    public ResponseEntity<?> quotesList(@PathVariable String id) {
        LogTrace.trace(log);
        return new ResponseEntity<>(deliveryService.quotes(id), HttpStatus.OK);
    }

    @PostMapping(value = "/{id}/quotes/{quotationId}/winner")
    public ResponseEntity<?> quotesWinner(@PathVariable String quotationId) {
        LogTrace.trace(log);
        return new ResponseEntity<>(deliveryService.quotationWinner(quotationId), HttpStatus.OK);
    }
}
