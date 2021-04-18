package co.edu.javeriana.farmaceutica.delivery.repository;

import co.edu.javeriana.farmaceutica.delivery.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, String>, DeliveryRepositoryCustom {
}
