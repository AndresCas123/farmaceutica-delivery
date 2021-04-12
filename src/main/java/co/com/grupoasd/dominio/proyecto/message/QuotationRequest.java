package co.com.grupoasd.dominio.proyecto.message;

import lombok.Data;

@Data
public class QuotationRequest {
    private Double price;
    private String supplier;
}
