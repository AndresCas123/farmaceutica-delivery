package co.edu.javeriana.farmaceutica.delivery.entity;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Municipio {
    @Id
    private String id;
    private String nombre;
}
