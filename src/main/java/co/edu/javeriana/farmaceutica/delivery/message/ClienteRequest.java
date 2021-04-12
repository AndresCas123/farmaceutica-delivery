package co.edu.javeriana.farmaceutica.delivery.message;

import lombok.Data;

@Data
public class ClienteRequest {
    private String id;
    private String tipoDcoumento;
    private String documento;
    private String nombre;
    private String username;
    private String password;
}
