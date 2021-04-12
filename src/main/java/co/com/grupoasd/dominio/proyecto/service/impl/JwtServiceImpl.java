package co.com.grupoasd.dominio.proyecto.service.impl;

import co.com.grupoasd.dominio.proyecto.service.JwtService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class JwtServiceImpl implements JwtService {

    public String generateToken(String username, String rol) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            return JWT.create()
                    .withIssuer("javeriana.edu.co")
                    .withSubject(username)
                    .withClaim("authorities", rol)
                    .sign(algorithm);
        } catch (JWTCreationException ex) {
            throw new RuntimeException(ex);
        }
    }
}
