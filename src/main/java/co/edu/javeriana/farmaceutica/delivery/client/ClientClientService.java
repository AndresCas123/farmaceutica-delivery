package co.edu.javeriana.farmaceutica.delivery.client;

import co.edu.javeriana.farmaceutica.delivery.message.ClientResponse;

public interface ClientClientService {
    ClientResponse get(String clientId);
}
