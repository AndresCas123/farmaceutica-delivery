package co.edu.javeriana.farmaceutica.delivery.message;

import lombok.Data;

@Data
public class CreateDeliveryRequest {
    private String source;
    private String destination;
    private Double weight;
    private Double volume;
    private String client;
}
