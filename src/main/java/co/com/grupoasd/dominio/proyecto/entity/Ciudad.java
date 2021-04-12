package co.com.grupoasd.dominio.proyecto.entity;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
public class Ciudad {
    @Id
    private String id;
    private String nombre;
    @ManyToOne
    private Municipio municipio;
}
