package co.edu.javeriana.farmaceutica.delivery.client.impl;

import co.edu.javeriana.farmaceutica.delivery.client.ClientClientService;
import co.edu.javeriana.farmaceutica.delivery.message.ClientResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ClientClientServiceImpl implements ClientClientService {

    @Value("${client.service.url}")
    private String clientServiceUrl;

    private final RestTemplate restTemplate;

    @Override
    public ClientResponse get(String clientId) {
        String endpoint = String.format("%s/clients/%s", clientServiceUrl, clientId);
        return restTemplate.getForObject(endpoint, ClientResponse.class);
    }
}
