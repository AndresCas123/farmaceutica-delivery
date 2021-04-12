package co.edu.javeriana.farmaceutica.delivery.message;

import lombok.Data;

@Data
public class QuotationRequest {
    private Double price;
    private String supplier;
}
