package co.com.grupoasd.dominio.proyecto.message;

import lombok.Data;

@Data
public class ProveedorRequest {
    private String id;
    private String tipoDcoumento;
    private String documento;
    private String nombre;
    private String username;
    private String password;
}
