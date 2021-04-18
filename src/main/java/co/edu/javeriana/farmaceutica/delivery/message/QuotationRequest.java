package co.edu.javeriana.farmaceutica.delivery.message;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class QuotationRequest {
    @ApiModelProperty(name = "price", notes = "Valor del despacho", required=true)
    private Double price;

    @ApiModelProperty(name = "supplier", notes = "Código de identificación del proveedor", required=true)
    private String supplier;

    @ApiModelProperty(name = "supplierName", notes = "Nombre del proveedor", required=false)
    private String supplierName;
}
