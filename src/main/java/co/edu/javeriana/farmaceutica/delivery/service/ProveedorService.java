package co.edu.javeriana.farmaceutica.delivery.service;

import co.edu.javeriana.farmaceutica.delivery.entity.Proveedor;
import co.edu.javeriana.farmaceutica.delivery.message.ProveedorRequest;

public interface ProveedorService {

    public Proveedor crear(ProveedorRequest proveedorRequest);
}
