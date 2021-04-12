package co.com.grupoasd.dominio.proyecto.entity;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class Delivery {
    @Id
    @Column(length = 64)
    private String id;

    @Column(nullable = false)
    private Date createdAt;

    @Column(nullable = false, length = 10)
    private String state;

    @Column(nullable = false, length = 5)
    private String source;

    @Column(nullable = false, length = 5)
    private String destination;

    @Column(nullable = false)
    private Double weight;

    @Column(nullable = false)
    private Double volume;

    @Column(nullable = false, length = 64)
    private String client;

    @Column(nullable = true, length = 64)
    private String quotation;
}
