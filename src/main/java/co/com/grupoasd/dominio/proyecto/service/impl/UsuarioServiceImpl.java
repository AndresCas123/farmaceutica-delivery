package co.com.grupoasd.dominio.proyecto.service.impl;

import co.com.grupoasd.dominio.proyecto.entity.Usuario;
import co.com.grupoasd.dominio.proyecto.exception.BadRequestException;
import co.com.grupoasd.dominio.proyecto.repository.UsuarioRepository;
import co.com.grupoasd.dominio.proyecto.util.IdUtil;
import lombok.AllArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import co.com.grupoasd.dominio.proyecto.service.UsuarioService;
import co.com.grupoasd.dominio.proyecto.util.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @inheritDoc
 * @author Nombres apellidos <correo@grupoasd.com.co>
 */
@Service
@Slf4j
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    //private final AuthenticationHandler authenticationHandler;
    private final UsuarioRepository usuarioRepository;

    
    /**
     * @inheritDoc
     */
    @Override
    @Transactional
    public Usuario crear(String username, String password) {
        LogTrace.trace(log);
        if(username == null || username.trim().isEmpty()) {
            throw new BadRequestException("Username invalido");
        }
        if(password == null || password.trim().isEmpty()) {
            throw new BadRequestException("Password invalido");
        }
        if(existe(username)) {
            throw new BadRequestException("Usuario ya existe con el username");
        }
        Usuario usuario = new Usuario();
        usuario.setId(IdUtil.generate());
        usuario.setUsername(username.trim().toLowerCase());
        usuario.setPassword(DigestUtils.sha256Hex(password.trim()));
        usuario.setActivo(true);
        // Ejemplo de obtencion del usuario de la peticion.
        // Claims claims  = authenticationHandler.getAuthentication();
        return usuarioRepository.save(usuario);
    }

    public boolean existe(String username) {
        return Optional.ofNullable(username)
                .map(user -> usuarioRepository.countByUsername(user.trim().toLowerCase()) > 0)
                .orElse(false);
    }

}
