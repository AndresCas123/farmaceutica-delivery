package co.edu.javeriana.farmaceutica.delivery.service.impl;

import co.edu.javeriana.farmaceutica.delivery.entity.Delivery;
import co.edu.javeriana.farmaceutica.delivery.entity.Quotation;
import co.edu.javeriana.farmaceutica.delivery.exception.BadRequestException;
import co.edu.javeriana.farmaceutica.delivery.message.CreateDeliveryRequest;
import co.edu.javeriana.farmaceutica.delivery.message.QuotationRequest;
import co.edu.javeriana.farmaceutica.delivery.repository.DeliveryRepository;
import co.edu.javeriana.farmaceutica.delivery.repository.QuotationRepository;
import co.edu.javeriana.farmaceutica.delivery.service.DeliveryService;
import co.edu.javeriana.farmaceutica.delivery.util.DateTimeUtil;
import co.edu.javeriana.farmaceutica.delivery.util.GlobalConstants;
import co.edu.javeriana.farmaceutica.delivery.util.IdUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final QuotationRepository quotationRepository;

    @Override
    public Delivery create(CreateDeliveryRequest createDeliveryRequest) {
        Delivery delivery = new Delivery();
        delivery.setId(IdUtil.generate());
        delivery.setCreatedAt(DateTimeUtil.now());
        delivery.setState(GlobalConstants.DELIVERY_STATE_QUOTATION);
        delivery.setSource(createDeliveryRequest.getSource());
        delivery.setSourceName(createDeliveryRequest.getSourceName());
        delivery.setDestination(createDeliveryRequest.getDestination());
        delivery.setDestinationName(createDeliveryRequest.getDestinationName());
        delivery.setWeight(createDeliveryRequest.getWeight());
        delivery.setVolume(createDeliveryRequest.getVolume());
        delivery.setClient(createDeliveryRequest.getClient());
        return deliveryRepository.save(delivery);
    }

    @Override
    public Optional<Delivery> get(String id) {
        return deliveryRepository.findById(id);
    }

    @Override
    public List<Delivery> list(String clientId, String state) {
        return deliveryRepository.findDeliveriesByClientAndState(clientId, state);
    }

    @Override
    public Quotation quotationCreate(String deliveryId, QuotationRequest quotationRequest) {
        return deliveryRepository.findById(deliveryId)
                .filter(delivery -> delivery.getState().equals(GlobalConstants.DELIVERY_STATE_QUOTATION))
                .map(delivery -> {
                    Quotation quotation = quotationRepository.findByDeliveryIdAndSupplier(deliveryId,
                            quotationRequest.getSupplier()).orElse(new Quotation());
                    if(quotation.getId() == null) {
                        quotation.setId(IdUtil.generate());
                    }
                    quotation.setCreatedAt(DateTimeUtil.now());
                    quotation.setDelivery(delivery);
                    quotation.setPrice(quotationRequest.getPrice());
                    quotation.setSupplier(quotationRequest.getSupplier());
                    quotation.setSupplierName(quotationRequest.getSupplierName());
                    return quotationRepository.save(quotation);
                })
                .map(quotation -> {
                    if(quotationRepository.countByDeliveryId(deliveryId) >= 3) {
                        // Envio de correo
                        System.out.println("Envio de correo");
                    }
                    return quotation;
                })
                .orElseThrow(() -> new BadRequestException("Delivery doesn't exist or state is not valid"));

    }

    @Override
    public List<Quotation> quotes(String deliveryId) {
        return quotationRepository.findAllByDeliveryId(deliveryId);
    }

    @Override
    public Quotation quotationWinner(String quotationId) {
        return quotationRepository.findById(quotationId)
                .map(quotation -> {
                    Delivery delivery = quotation.getDelivery();
                    delivery.setQuotation(quotation.getId());
                    delivery.setState(GlobalConstants.DELIVERY_SATE_ASSIGNED);
                    deliveryRepository.save(delivery);
                    return quotation;
                })
                .orElseThrow(() -> new BadRequestException("Quotation doesn't exist"));
    }
}
