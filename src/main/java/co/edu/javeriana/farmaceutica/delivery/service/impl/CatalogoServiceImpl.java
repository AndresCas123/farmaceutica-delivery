package co.edu.javeriana.farmaceutica.delivery.service.impl;

import co.edu.javeriana.farmaceutica.delivery.entity.Catalogo;
import co.edu.javeriana.farmaceutica.delivery.entity.Ciudad;
import co.edu.javeriana.farmaceutica.delivery.entity.Proveedor;
import co.edu.javeriana.farmaceutica.delivery.message.CatalogoRequest;
import co.edu.javeriana.farmaceutica.delivery.repository.CatalogoRepository;
import co.edu.javeriana.farmaceutica.delivery.repository.CiudadRepository;
import co.edu.javeriana.farmaceutica.delivery.repository.ProveedorRepository;
import co.edu.javeriana.farmaceutica.delivery.service.CatalogoService;
import co.edu.javeriana.farmaceutica.delivery.util.IdUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class CatalogoServiceImpl implements CatalogoService {

    private final CatalogoRepository catalogoRepository;
    private final CiudadRepository ciudadRepository;
    private final ProveedorRepository proveedorRepository;

    @Override
    public void crear(List<CatalogoRequest> catalogoRequest) {
        catalogoRequest.forEach(request -> {
            Optional<Ciudad> ciudadOrigen = ciudadRepository.findById(request.getOrigen());
            Optional<Ciudad> ciudadDestino = ciudadRepository.findById(request.getDestino());
            Optional<Proveedor> proveedor = proveedorRepository.findById(request.getProveedor());
            if(ciudadOrigen.isPresent() && ciudadDestino.isPresent() && proveedor.isPresent()) {
                Catalogo catalogo = new Catalogo();
                catalogo.setId(IdUtil.generate());
                catalogo.setPesoMinimo(request.getPesoMinimo());
                catalogo.setPesoMaximo(request.getPesoMaximo());
                catalogo.setOrigen(ciudadOrigen.get());
                catalogo.setDestino(ciudadDestino.get());
                catalogo.setPrecio(request.getPrecio());
                catalogo.setProveedor(proveedor.get());
                catalogoRepository.save(catalogo);
            }
        });
    }
}
