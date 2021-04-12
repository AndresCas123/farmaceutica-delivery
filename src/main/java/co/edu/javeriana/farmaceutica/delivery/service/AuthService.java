package co.edu.javeriana.farmaceutica.delivery.service;

import co.edu.javeriana.farmaceutica.delivery.message.AuthRequest;
import co.edu.javeriana.farmaceutica.delivery.message.AuthResponse;
import java.util.Optional;

public interface AuthService {
    Optional<AuthResponse> login(AuthRequest authRequest);
}
