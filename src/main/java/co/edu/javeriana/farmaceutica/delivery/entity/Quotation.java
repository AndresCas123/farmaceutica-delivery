package co.edu.javeriana.farmaceutica.delivery.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Data
@Entity
public class Quotation {
    @Id
    @ApiModelProperty(name = "id", notes = "Identificador de la cotización", required=false)
    @Column(length = 64)
    private String id;

    @ApiModelProperty(name = "createdAt", notes = "Fecha de creación de la cotización", required=false)
    @Column(nullable = false)
    private Date createdAt;

    @ApiModelProperty(name = "supplier", notes = "Código de identificación del proveedor de la cotización", required=false)
    @Column(nullable = false, length = 64)
    private String supplier;

    @ApiModelProperty(name = "supplierName", notes = "Nombre del proveedor de la cotización", required=false)
    @Column(nullable = false, length = 100)
    private String supplierName;

    @ApiModelProperty(name = "price", notes = "Valor del despacho", required=false)
    @Column(nullable = false)
    private Double price;

    @ApiModelProperty(name = "delivery", notes = "Despacho cotizado", required=false)
    @ManyToOne(optional = false)
    private Delivery delivery;

    @ApiModelProperty(name = "state", notes = "Estado de la cotización. Estados posibles: " +
            "\n\nquotation: La cotización se encuentra en proceso de revisión por parte del cliente." +
            "\n\nwinner: La cotización fue seleccionada como ganadora por parte del cliente." +
            "\n\nloser: La cotización no fue seleccionada por el cliente por lo cual fue perdedora.", required=true)
    @Column(nullable = false, length = 10)
    private String state;
}
