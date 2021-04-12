package co.edu.javeriana.farmaceutica.delivery.repository;

import co.edu.javeriana.farmaceutica.delivery.entity.Quotation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuotationRepository extends JpaRepository<Quotation, String> {
    Optional<Quotation> findByDeliveryIdAndSupplier(String deliveryId, String supplier);
    long countByDeliveryId(String deliveryId);
    List<Quotation> findAllByDeliveryId(String deliveryId);
}
