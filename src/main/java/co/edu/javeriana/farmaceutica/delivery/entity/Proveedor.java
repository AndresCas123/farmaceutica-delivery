package co.edu.javeriana.farmaceutica.delivery.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@Entity
public class Proveedor {
    @Id
    private String id;
    private String tipoDcoumento;
    private String documento;
    private String nombre;
    @ManyToOne
    private Usuario usuario;

}
