package co.edu.javeriana.farmaceutica.delivery.service.impl;

import co.edu.javeriana.farmaceutica.delivery.entity.Cliente;
import co.edu.javeriana.farmaceutica.delivery.entity.Usuario;
import co.edu.javeriana.farmaceutica.delivery.message.ClienteRequest;
import co.edu.javeriana.farmaceutica.delivery.repository.ClienteRepository;
import co.edu.javeriana.farmaceutica.delivery.service.ClienteService;
import co.edu.javeriana.farmaceutica.delivery.service.UsuarioService;
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
