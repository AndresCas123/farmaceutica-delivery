package co.edu.javeriana.farmaceutica.delivery.service;

import co.edu.javeriana.farmaceutica.delivery.message.CatalogoRequest;

import java.util.List;

public interface CatalogoService {
    void crear(List<CatalogoRequest> catalogoRequest);
}
