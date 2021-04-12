package co.edu.javeriana.farmaceutica.delivery.service.impl;

import co.edu.javeriana.farmaceutica.delivery.entity.Proveedor;
import co.edu.javeriana.farmaceutica.delivery.entity.Usuario;
import co.edu.javeriana.farmaceutica.delivery.message.ProveedorRequest;
import co.edu.javeriana.farmaceutica.delivery.repository.ProveedorRepository;
import co.edu.javeriana.farmaceutica.delivery.service.ProveedorService;
import co.edu.javeriana.farmaceutica.delivery.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ProveedorServiceImpl implements ProveedorService {

    private final UsuarioService usuarioService;
    private final ProveedorRepository proveedorRepository;

    @Transactional
    public Proveedor crear(ProveedorRequest proveedorRequest) {
        Usuario usuario = usuarioService.crear(proveedorRequest.getUsername(), proveedorRequest.getPassword());
        Proveedor proveedor = new Proveedor();
        proveedor.setId(usuario.getId());
        proveedor.setTipoDcoumento(proveedorRequest.getTipoDcoumento());
        proveedor.setDocumento(proveedorRequest.getDocumento());
        proveedor.setNombre(proveedorRequest.getNombre());
        proveedor.setUsuario(usuario);
        return proveedorRepository.save(proveedor);
    }
}
