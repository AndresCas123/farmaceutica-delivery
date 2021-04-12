package co.com.grupoasd.dominio.proyecto.repository;

import co.com.grupoasd.dominio.proyecto.entity.Quotation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuotationRepository extends JpaRepository<Quotation, String> {
    Optional<Quotation> findByDeliveryIdAndSupplier(String deliveryId, String supplier);
    long countByDeliveryId(String deliveryId);
    List<Quotation> findAllByDeliveryId(String deliveryId);
}
