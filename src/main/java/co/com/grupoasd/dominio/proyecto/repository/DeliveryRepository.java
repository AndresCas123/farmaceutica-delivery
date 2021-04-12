package co.com.grupoasd.dominio.proyecto.repository;

import co.com.grupoasd.dominio.proyecto.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, String> {
}
