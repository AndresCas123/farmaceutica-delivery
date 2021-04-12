package co.edu.javeriana.farmaceutica.delivery.repository;

import co.edu.javeriana.farmaceutica.delivery.entity.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface ClienteRepository extends CrudRepository<Cliente, String> {
}
