package co.com.sofka.mscuentas.service;

import org.springframework.stereotype.Service;

import co.com.sofka.mscuentas.service.external.msclientes.ClientesRestClient;
import co.com.sofka.mscuentas.service.interfaces.IClienteService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ClientesService implements IClienteService {

    private final ClientesRestClient clientesRestClient;

    @Override
    public String obtenerNombreClientePorId(Long clienteId) {

        var cliente = clientesRestClient.getClienteById(clienteId);

        return cliente.nombre();
    }

}
