package co.edu.javeriana.farmaceutica.delivery.repository;

import co.edu.javeriana.farmaceutica.delivery.entity.Delivery;

import java.util.List;

public interface DeliveryRepositoryCustom {
    List<Delivery> findDeliveriesByClientAndState(String clientId, String state);
}
