package co.edu.javeriana.farmaceutica.delivery.message;

import lombok.Data;

@Data
public class CatalogoRequest {
    private long pesoMinimo;
    private long pesoMaximo;
    private String origen;
    private String destino;
    private double precio;
    private String proveedor;
}
