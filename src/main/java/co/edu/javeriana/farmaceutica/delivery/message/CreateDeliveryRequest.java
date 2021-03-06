package co.edu.javeriana.farmaceutica.delivery.message;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CreateDeliveryRequest {
    @ApiModelProperty(name = "description", notes = "Descripción del despacho", required=true)
    private String description;

    @ApiModelProperty(name = "source", notes = "Código de la ciudad de origen del despacho", required=true , example="11001")
    private String source;

    @ApiModelProperty(name = "sourceName", notes = "Nombre de la ciudad de origen del despacho", required=false , example="BOGOTA")
    private String sourceName;

    @ApiModelProperty(name = "destination", notes = "Código de la ciudad de destino del despacho", required=true , example="11001")
    private String destination;

    @ApiModelProperty(name = "destinationName", notes = "Nombre de la ciudad de destino del despacho", required=false, example="BOGOTA")
    private String destinationName;

    @ApiModelProperty(name = "weight", notes = "Peso en kilogramos de la mercancia", required=true)
    private Double weight;

    @ApiModelProperty(name = "volume", notes = "Volumen en centímetros de la mercancia", required=true)
    private Double volume;

    @ApiModelProperty(name = "client", notes = "Código de identificación del cliente que solicita el despacho", required=false)
    private String client;
}
