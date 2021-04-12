package co.com.grupoasd.dominio.proyecto.message;

import lombok.Data;

@Data
public class CreateDeliveryRequest {
    private String source;
    private String destination;
    private Double weight;
    private Double volume;
    private String client;
}
