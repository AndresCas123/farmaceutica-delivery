package co.edu.javeriana.farmaceutica.delivery.service.impl;

import co.edu.javeriana.farmaceutica.delivery.message.AuthRequest;
import co.edu.javeriana.farmaceutica.delivery.message.AuthResponse;
import co.edu.javeriana.farmaceutica.delivery.repository.ClienteRepository;
import co.edu.javeriana.farmaceutica.delivery.repository.ProveedorRepository;
import co.edu.javeriana.farmaceutica.delivery.repository.UsuarioRepository;
import co.edu.javeriana.farmaceutica.delivery.service.AuthService;
import co.edu.javeriana.farmaceutica.delivery.service.JwtService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final ProveedorRepository proveedorRepository;
    private final ClienteRepository clienteRepository;

    @Override
    public Optional<AuthResponse> login(AuthRequest authRequest) {
        if(authRequest == null || authRequest.getUsername() == null ||
                authRequest.getUsername().trim().isEmpty()) {
            return Optional.empty();
        }
        if(authRequest == null || authRequest.getPassword() == null ||
                authRequest.getPassword().trim().isEmpty()) {
            return Optional.empty();
        }
        String username = authRequest.getUsername().trim().toLowerCase();
        return usuarioRepository.findByUsernameAndPasswordAndActivo(username,
                DigestUtils.sha256Hex(authRequest.getPassword().trim()), true)
                .map(usuario -> {
                    String rol = proveedorRepository.findById(usuario.getId())
                            .map(proveedor -> "proveedor")
                            .orElse("cliente");
                    AuthResponse res = new AuthResponse();
                    res.setToken(jwtService.generateToken(username, rol));
                    return Optional.of(res);
                }).orElse(Optional.empty());
    }
}
