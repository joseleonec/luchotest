package co.com.sofka.luchotest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.sofka.luchotest.dto.ClienteCreateDTO;
import co.com.sofka.luchotest.mapper.ClienteMapper;
import co.com.sofka.luchotest.persistence.entity.ClienteEntity;
import co.com.sofka.luchotest.service.ClienteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteMapper clienteMapper;

    private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteEntity> crearCliente(@Valid @RequestBody ClienteCreateDTO cliente) {

        ClienteEntity clienteEntity = clienteMapper.toEntity(cliente);

        ClienteEntity clienteCreado = clienteService.crearCliente(clienteEntity);

        return ResponseEntity.ok(clienteCreado);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteEntity> obtenerClientePorId(@PathVariable Long id) {
        ClienteEntity clienteEntity = clienteService.getClienteById(id);
        return ResponseEntity.ok(clienteEntity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteEntity> actualizarCliente(@PathVariable Long id, @Valid @RequestBody ClienteCreateDTO cliente) {

        ClienteEntity clienteActualizado = clienteMapper.toEntity(cliente);
        clienteActualizado.setId(id);

        ClienteEntity clienteGuardado = clienteService.updateCliente(clienteActualizado);

        return ResponseEntity.ok(clienteGuardado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        clienteService.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }

}
