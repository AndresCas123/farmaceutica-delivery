package co.com.grupoasd.dominio.proyecto.service.impl;

import co.com.grupoasd.dominio.proyecto.entity.Cliente;
import co.com.grupoasd.dominio.proyecto.entity.Proveedor;
import co.com.grupoasd.dominio.proyecto.entity.Usuario;
import co.com.grupoasd.dominio.proyecto.message.ProveedorRequest;
import co.com.grupoasd.dominio.proyecto.repository.ProveedorRepository;
import co.com.grupoasd.dominio.proyecto.service.ProveedorService;
import co.com.grupoasd.dominio.proyecto.service.UsuarioService;
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
