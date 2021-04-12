package co.com.grupoasd.dominio.proyecto.repository;

import co.com.grupoasd.dominio.proyecto.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<Usuario, String> {
    long countByUsername(String username);
    Optional<Usuario> findByUsernameAndPasswordAndActivo(String username, String password, boolean activo);
}