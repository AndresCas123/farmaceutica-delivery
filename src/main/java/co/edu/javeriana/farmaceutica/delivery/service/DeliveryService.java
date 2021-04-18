package co.edu.javeriana.farmaceutica.delivery.service;

import co.edu.javeriana.farmaceutica.delivery.entity.Delivery;
import co.edu.javeriana.farmaceutica.delivery.entity.Quotation;
import co.edu.javeriana.farmaceutica.delivery.message.CreateDeliveryRequest;
import co.edu.javeriana.farmaceutica.delivery.message.QuotationRequest;
import java.util.List;
import java.util.Optional;

public interface DeliveryService {
    Delivery create(CreateDeliveryRequest createDeliveryRequest);
    Optional<Delivery> get(String id);
    List<Delivery> list(String clientId, String state);
    Quotation quotationCreate(String deliveryId, QuotationRequest quotationRequest);
    List<Quotation> quotes(String deliveryId);
    Quotation quotationWinner(String quotationId);
}
