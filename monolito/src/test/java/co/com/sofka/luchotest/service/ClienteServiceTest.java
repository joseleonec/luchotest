package co.com.sofka.luchotest.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import co.com.sofka.luchotest.exceptions.ResourceAlreadyExistsException;
import co.com.sofka.luchotest.persistence.entity.ClienteEntity;
import co.com.sofka.luchotest.persistence.repositroy.ClienteRepository;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @Test
    @DisplayName("crearCliente: Should save cliente when it does not exist")
    void testCrearClienteSuccess() {
        // Arrange
        ClienteEntity clienteToSave = new ClienteEntity();
        clienteToSave.setClienteId("12345");
        clienteToSave.setNombre("Juan Perez");
        clienteToSave.setContrasena("1234");
        clienteToSave.setEstado("True");

        when(clienteRepository.existsByClienteId("12345")).thenReturn(false);
        when(clienteRepository.save(any(ClienteEntity.class))).thenReturn(clienteToSave);

        // Act
        ClienteEntity savedCliente = clienteService.crearCliente(clienteToSave);

        // Assert
        assertNotNull(savedCliente);
        assertEquals("12345", savedCliente.getClienteId());
        verify(clienteRepository, times(1)).existsByClienteId("12345");
        verify(clienteRepository, times(1)).save(clienteToSave);
    }

    @Test
    @DisplayName("crearCliente: Should throw ResourceAlreadyExistsException when clienteId exists")
    void testCrearClienteAlreadyExists() {
        // Arrange
        ClienteEntity clienteToSave = new ClienteEntity();
        clienteToSave.setClienteId("12345");

        when(clienteRepository.existsByClienteId("12345")).thenReturn(true);

        // Act & Assert
        assertThrows(ResourceAlreadyExistsException.class, () -> {
            clienteService.crearCliente(clienteToSave);
        });

        verify(clienteRepository, times(1)).existsByClienteId("12345");
        verify(clienteRepository, never()).save(any(ClienteEntity.class));
    }

    @Test
    @DisplayName("updateCliente: Should update cliente when id exists")
    void testUpdateClienteSuccess() {
        // Arrange
        Long id = 1L;
        ClienteEntity clienteToUpdate = new ClienteEntity();
        clienteToUpdate.setId(id);
        clienteToUpdate.setNombre("Updated Name");

        when(clienteRepository.existsById(id)).thenReturn(true);
        when(clienteRepository.save(clienteToUpdate)).thenReturn(clienteToUpdate);

        // Act
        ClienteEntity result = clienteService.updateCliente(clienteToUpdate);

        // Assert
        assertNotNull(result);
        assertEquals("Updated Name", result.getNombre());
        verify(clienteRepository, times(1)).existsById(id);
        verify(clienteRepository, times(1)).save(clienteToUpdate);
    }

    @Test
    @DisplayName("updateCliente: Should throw NoSuchElementException when id does not exist")
    void testUpdateClienteNotFound() {
        // Arrange
        Long id = 1L;
        ClienteEntity clienteToUpdate = new ClienteEntity();
        clienteToUpdate.setId(id);

        when(clienteRepository.existsById(id)).thenReturn(false);

        // Act & Assert
        assertThrows(NoSuchElementException.class, () -> {
            clienteService.updateCliente(clienteToUpdate);
        });

        verify(clienteRepository, times(1)).existsById(id);
        verify(clienteRepository, never()).save(any(ClienteEntity.class));
    }

    @Test
    @DisplayName("getClienteById: Should return cliente when found")
    void testGetClienteByIdSuccess() {
        // Arrange
        Long id = 1L;
        ClienteEntity cliente = new ClienteEntity();
        cliente.setId(id);
        cliente.setNombre("Test Cliente");

        when(clienteRepository.findById(id)).thenReturn(Optional.of(cliente));

        // Act
        ClienteEntity result = clienteService.getClienteById(id);

        // Assert
        assertNotNull(result);
        assertEquals(id, result.getId());
        verify(clienteRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("getClienteById: Should throw NoSuchElementException when not found")
    void testGetClienteByIdNotFound() {
        // Arrange
        Long id = 1L;
        when(clienteRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NoSuchElementException.class, () -> {
            clienteService.getClienteById(id);
        });

        verify(clienteRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("deleteCliente: Should delete cliente when id exists")
    void testDeleteClienteSuccess() {
        // Arrange
        Long id = 1L;
        when(clienteRepository.existsById(id)).thenReturn(true);

        // Act
        clienteService.deleteCliente(id);

        // Assert
        verify(clienteRepository, times(1)).existsById(id);
        verify(clienteRepository, times(1)).deleteById(id);
    }

    @Test
    @DisplayName("deleteCliente: Should throw NoSuchElementException when id does not exist")
    void testDeleteClienteNotFound() {
        // Arrange
        Long id = 1L;
        when(clienteRepository.existsById(id)).thenReturn(false);

        // Act & Assert
        assertThrows(NoSuchElementException.class, () -> {
            clienteService.deleteCliente(id);
        });

        verify(clienteRepository, times(1)).existsById(id);
        verify(clienteRepository, never()).deleteById(any());
    }
}
