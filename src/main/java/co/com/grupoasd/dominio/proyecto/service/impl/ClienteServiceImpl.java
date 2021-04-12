package co.com.grupoasd.dominio.proyecto.service.impl;

import co.com.grupoasd.dominio.proyecto.entity.Cliente;
import co.com.grupoasd.dominio.proyecto.entity.Usuario;
import co.com.grupoasd.dominio.proyecto.message.ClienteRequest;
import co.com.grupoasd.dominio.proyecto.repository.ClienteRepository;
import co.com.grupoasd.dominio.proyecto.service.ClienteService;
import co.com.grupoasd.dominio.proyecto.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final UsuarioService usuarioService;
    private final ClienteRepository clienteRepository;

    @Transactional
    public Cliente crear(ClienteRequest clienteRequest) {
        Usuario usuario = usuarioService.crear(clienteRequest.getUsername(), clienteRequest.getPassword());
        Cliente cliente = new Cliente();
        cliente.setId(usuario.getId());
        cliente.setTipoDcoumento(clienteRequest.getTipoDcoumento());
        cliente.setDocumento(clienteRequest.getDocumento());
        cliente.setNombre(clienteRequest.getNombre());
        cliente.setUsuario(usuario);
        return clienteRepository.save(cliente);
    }
}
