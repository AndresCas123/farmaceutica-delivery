package co.edu.javeriana.farmaceutica.delivery.message;

import lombok.Data;

@Data
public class ClientResponse {
    private String id;
    private String documentType;
    private String document;
    private String name;
    private String email;
    private String phone;
}
