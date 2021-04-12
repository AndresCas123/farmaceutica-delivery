package co.com.grupoasd.dominio.proyecto.service;

import co.com.grupoasd.dominio.proyecto.entity.Proveedor;
import co.com.grupoasd.dominio.proyecto.message.ProveedorRequest;

public interface ProveedorService {

    public Proveedor crear(ProveedorRequest proveedorRequest);
}
