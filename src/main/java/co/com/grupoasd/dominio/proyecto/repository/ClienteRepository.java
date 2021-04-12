package co.com.grupoasd.dominio.proyecto.repository;

import co.com.grupoasd.dominio.proyecto.entity.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface ClienteRepository extends CrudRepository<Cliente, String> {
}
