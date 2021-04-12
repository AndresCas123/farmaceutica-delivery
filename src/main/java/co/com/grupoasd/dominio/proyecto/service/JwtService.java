package co.com.grupoasd.dominio.proyecto.service;

public interface JwtService {
    String generateToken(String username, String rol);

}
