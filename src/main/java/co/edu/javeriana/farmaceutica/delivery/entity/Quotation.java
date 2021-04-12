package co.edu.javeriana.farmaceutica.delivery.entity;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Data
@Entity
public class Quotation {
    @Id
    @Column(length = 64)
    private String id;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false, length = 64)
    private String supplier;

    @Column(nullable = false)
    private Double price;

    @ManyToOne(optional = false)
    private Delivery delivery;
}
