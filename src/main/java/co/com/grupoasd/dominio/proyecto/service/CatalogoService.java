package co.com.grupoasd.dominio.proyecto.service;

import co.com.grupoasd.dominio.proyecto.message.CatalogoRequest;
import java.util.List;

public interface CatalogoService {
    void crear(List<CatalogoRequest> catalogoRequest);
}
