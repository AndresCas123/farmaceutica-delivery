package co.edu.javeriana.farmaceutica.delivery.service.impl;

import co.edu.javeriana.farmaceutica.delivery.entity.Delivery;
import co.edu.javeriana.farmaceutica.delivery.entity.Quotation;
import co.edu.javeriana.farmaceutica.delivery.exception.BadRequestException;
import co.edu.javeriana.farmaceutica.delivery.message.CreateDeliveryRequest;
import co.edu.javeriana.farmaceutica.delivery.message.QuotationRequest;
import co.edu.javeriana.farmaceutica.delivery.repository.DeliveryRepository;
import co.edu.javeriana.farmaceutica.delivery.repository.QuotationRepository;
import co.edu.javeriana.farmaceutica.delivery.service.DeliveryService;
import co.edu.javeriana.farmaceutica.delivery.service.MailService;
import co.edu.javeriana.farmaceutica.delivery.util.DateTimeUtil;
import co.edu.javeriana.farmaceutica.delivery.util.GlobalConstants;
import co.edu.javeriana.farmaceutica.delivery.util.IdUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final QuotationRepository quotationRepository;
    private final MailService mailService;

    @Override
    public Delivery create(CreateDeliveryRequest createDeliveryRequest) {
        Delivery delivery = new Delivery();
        delivery.setId(IdUtil.generate());
        delivery.setDescription(createDeliveryRequest.getDescription());
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
                    quotation.setState(GlobalConstants.QUOTATION_SATE_QUOTATION);
                    return quotationRepository.save(quotation);
                })
                .map(quotation -> {
                    if(quotationRepository.countByDeliveryId(deliveryId) >= 3) {
                        // Envio de correo
                        try {
                            mailService.send("juancastellanosm@gmail.com",
                                    "Farmacéutica La Javeriana - Cotizaciones de despacho", getMailContent(deliveryId));
                        } catch(MessagingException ex) {
                            log.error(ex.getMessage(), ex);
                        }
                        log.info("Envio de correo");
                    }
                    return quotation;
                })
                .orElseThrow(() -> new BadRequestException("Delivery doesn't exist or state is not valid"));

    }

    @Override
    public List<Quotation> quotes(String deliveryId) {
        return quotationRepository.findAllByDeliveryId(deliveryId);
    }

    @Transactional
    @Override
    public Quotation quotationWinner(String quotationId) {
        return quotationRepository.findById(quotationId)
                .map(quotation -> {
                    Delivery delivery = quotation.getDelivery();
                    quotationRepository.findAllByDeliveryId(delivery.getId()).forEach(q -> {
                        if(q.getId().equals(quotationId)) {
                            q.setState(GlobalConstants.QUOTATION_SATE_WINNER);
                        }
                        else {
                            q.setState(GlobalConstants.QUOTATION_SATE_LOSER);
                        }
                        quotationRepository.save(q);
                    });
                    delivery.setQuotation(quotation.getId());
                    delivery.setState(GlobalConstants.DELIVERY_SATE_ASSIGNED);
                    deliveryRepository.save(delivery);
                    return quotation;
                })
                .orElseThrow(() -> new BadRequestException("Quotation doesn't exist"));
    }

    private String getMailContent(String deliveryId) {
        StringBuilder content = new StringBuilder();
        deliveryRepository.findById(deliveryId).map(delivery -> {
            content.append("<p><strong>Despacho:</strong> " + delivery.getDescription() + "</p>");
            content.append("<p>Las siguientes cotizaciones de despacho se encuentran disponibles por parte de nuestros " +
                    "proveedores: <p>");
            content.append("<table>");
            content.append("<tr><td><strong>Proveedor</strong></td><td><strong>Precio</strong></td></tr>");
            quotationRepository.findAllByDeliveryId(deliveryId).forEach(quotation -> {
                content.append("<tr>");
                content.append("<td>" + quotation.getSupplierName() + "</td>");
                content.append("<td>" + quotation.getPrice() + "</td>");
                content.append("</tr>");
            });
            content.append("</table>");
            return delivery;
        }).orElseThrow(() -> new RuntimeException("No existe delivery con el id suministrado"));
        return content.toString();

    }
}
