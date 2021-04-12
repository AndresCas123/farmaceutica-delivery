package co.edu.javeriana.farmaceutica.delivery.entity;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Usuario {
    @Id
    private String id;
    private String username;
    private String password;
    private boolean activo;
}