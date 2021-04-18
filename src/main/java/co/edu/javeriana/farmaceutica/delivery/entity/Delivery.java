package co.edu.javeriana.farmaceutica.delivery.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class Delivery {
    @Id
    @ApiModelProperty(name = "id", notes = "Identificador del despacho", required=false)
    @Column(length = 64)
    private String id;

    @Column(nullable = false)
    @ApiModelProperty(name = "createdAt", notes = "Fecha de creación de la solicitud despacho", required=false)
    private Date createdAt;

    @ApiModelProperty(name = "state", notes = "Estado de la solicitud despacho. Estados posibles: " +
            "\n\nquotation: El despacho se encuentra en cotización por parte de los proveedores." +
            "\n\nassigned: El despacho ya tiene un proveedor seleccionado con la cotización ganadora.", required=false)
    @Column(nullable = false, length = 10)
    private String state;

    @ApiModelProperty(name = "source", notes = "Código de la ciudad de origen del despacho", required=true , example="11001")
    @Column(nullable = false, length = 5)
    private String source;

    @ApiModelProperty(name = "sourceName", notes = "Nombre de la ciudad de origen del despacho", required=true , example="11001")
    @Column(nullable = true, length = 70)
    private String sourceName;

    @ApiModelProperty(name = "destination", notes = "Código de la ciudad de destino del despacho", required=true , example="11001")
    @Column(nullable = false, length = 5)
    private String destination;

    @ApiModelProperty(name = "destinationName", notes = "Nombre de la ciudad de destino del despacho", required=true , example="11001")
    @Column(nullable = true, length = 70)
    private String destinationName;

    @ApiModelProperty(name = "weight", notes = "Peso en kilogramos de la mercancia", required=true)
    @Column(nullable = false)
    private Double weight;

    @ApiModelProperty(name = "volume", notes = "Volumen en centímetros de la mercancia", required=true)
    @Column(nullable = false)
    private Double volume;

    @ApiModelProperty(name = "client", notes = "Código de identificación del cliente que solicita el despacho", required=true)
    @Column(nullable = false, length = 64)
    private String client;

    @ApiModelProperty(name = "quotation", notes = "Código de la cotización del proveedor ganador.", required=false)
    @Column(nullable = true, length = 64)
    private String quotation;
}
