package co.com.grupoasd.dominio.proyecto.repository;

import co.com.grupoasd.dominio.proyecto.entity.Ciudad;
import org.springframework.data.repository.CrudRepository;

public interface CiudadRepository extends CrudRepository<Ciudad, String>  {
}
