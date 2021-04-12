package co.edu.javeriana.farmaceutica.delivery.service;

import co.edu.javeriana.farmaceutica.delivery.entity.Cliente;
import co.edu.javeriana.farmaceutica.delivery.message.ClienteRequest;

public interface ClienteService {

    public Cliente crear(ClienteRequest clienteRequest);
}
