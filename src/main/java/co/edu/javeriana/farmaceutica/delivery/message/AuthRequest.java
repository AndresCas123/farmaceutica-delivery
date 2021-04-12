package co.edu.javeriana.farmaceutica.delivery.message;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}
