package co.com.grupoasd.dominio.proyecto.message;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}
