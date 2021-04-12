package co.com.grupoasd.dominio.proyecto.service;

import co.com.grupoasd.dominio.proyecto.entity.Delivery;
import co.com.grupoasd.dominio.proyecto.entity.Quotation;
import co.com.grupoasd.dominio.proyecto.message.CreateDeliveryRequest;
import co.com.grupoasd.dominio.proyecto.message.QuotationRequest;
import java.util.List;
import java.util.Optional;

public interface DeliveryService {
    Delivery create(CreateDeliveryRequest createDeliveryRequest);
    Optional<Delivery> get(String id);
    List<Delivery> list();
    Quotation quotationCreate(String deliveryId, QuotationRequest quotationRequest);
    List<Quotation> quotes(String deliveryId);
    Quotation quotationWinner(String quotationId);
}
