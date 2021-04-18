package co.edu.javeriana.farmaceutica.delivery.repository;

import co.edu.javeriana.farmaceutica.delivery.entity.Delivery;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class DeliveryRepositoryCustomImpl implements DeliveryRepositoryCustom {

    private final EntityManager em;

    @Override
    public List<Delivery> findDeliveriesByClientAndState(String clientId, String state) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Delivery> cq = cb.createQuery(Delivery.class);
        Root<Delivery> delivery = cq.from(Delivery.class);
        List<Predicate> predicates = new ArrayList<>();
        if (clientId != null && !clientId.trim().isEmpty()) {
            predicates.add(cb.equal(delivery.get("client"), clientId));
        }
        if (state != null && !state.trim().isEmpty()) {
            predicates.add(cb.equal(delivery.get("state"), state));
        }
        cq.where(predicates.toArray(new Predicate[0]));
        return em.createQuery(cq).getResultList();
    }
}
