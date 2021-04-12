package co.com.grupoasd.dominio.proyecto.service;

import co.com.grupoasd.dominio.proyecto.entity.Cliente;
import co.com.grupoasd.dominio.proyecto.message.ClienteRequest;

public interface ClienteService {

    public Cliente crear(ClienteRequest clienteRequest);
}
