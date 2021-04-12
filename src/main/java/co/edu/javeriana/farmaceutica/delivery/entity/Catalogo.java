package co.edu.javeriana.farmaceutica.delivery.entity;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
public class Catalogo {
    @Id
    private String id;
    private long pesoMinimo;
    private long pesoMaximo;
    @ManyToOne
    private Ciudad origen;
    @ManyToOne
    private Ciudad destino;
    private double precio;
    @ManyToOne
    private Proveedor proveedor;
}
