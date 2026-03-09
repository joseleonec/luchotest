package co.com.sofka.luchotest.infrastructure.adapter.in.web;

import co.com.sofka.luchotest.application.mapper.ClienteDTOMapper;
import co.com.sofka.luchotest.domain.model.Cliente;
import co.com.sofka.luchotest.domain.port.in.ClienteUseCase;
import co.com.sofka.luchotest.infrastructure.adapter.in.dto.ClienteCreateDTO;
import co.com.sofka.luchotest.infrastructure.adapter.in.dto.ClienteResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * REST Controller for Cliente operations (Inbound Adapter).
 * This controller uses the hexagonal architecture pattern.
 */
@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteRestController {

    private final ClienteUseCase clienteUseCase;
    private final ClienteDTOMapper clienteDTOMapper;

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> crearCliente(@Valid @RequestBody ClienteCreateDTO clienteDTO) {
        Cliente cliente = clienteDTOMapper.toDomain(clienteDTO);
        Cliente clienteCreado = clienteUseCase.crearCliente(cliente);
        return ResponseEntity.ok(clienteDTOMapper.toDTO(clienteCreado));
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> obtenerTodosLosClientes() {
        List<Cliente> clientes = clienteUseCase.getAllClientes();
        List<ClienteResponseDTO> clientesDTO = clientes.stream()
                .map(clienteDTOMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(clientesDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> obtenerClientePorId(@PathVariable Long id) {
        Cliente cliente = clienteUseCase.getClienteById(id);
        return ResponseEntity.ok(clienteDTOMapper.toDTO(cliente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> actualizarCliente(
            @PathVariable Long id,
            @Valid @RequestBody ClienteCreateDTO clienteDTO) {
        Cliente cliente = clienteDTOMapper.toDomain(clienteDTO);
        cliente.setId(id);
        Cliente clienteActualizado = clienteUseCase.updateCliente(cliente);
        return ResponseEntity.ok(clienteDTOMapper.toDTO(clienteActualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        clienteUseCase.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }
}
