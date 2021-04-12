package co.com.grupoasd.dominio.proyecto.service;

import co.com.grupoasd.dominio.proyecto.message.AuthRequest;
import co.com.grupoasd.dominio.proyecto.message.AuthResponse;
import java.util.Optional;

public interface AuthService {
    Optional<AuthResponse> login(AuthRequest authRequest);
}
