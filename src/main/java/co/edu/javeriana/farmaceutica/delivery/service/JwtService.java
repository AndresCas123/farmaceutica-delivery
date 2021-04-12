package co.edu.javeriana.farmaceutica.delivery.service;

public interface JwtService {
    String generateToken(String username, String rol);

}
