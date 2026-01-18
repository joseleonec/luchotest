package co.com.sofka.mscuentas.service.external.msclientes;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        name = "ms-clientes",
        url = "${api.msclientes.url}",
        configuration = ClienteFeignConfig.class
)
public interface ClientesRestClient {

    @GetMapping("/clientes/{clienteId}")
    ClienteDTO getClienteById(@PathVariable Long clienteId);

}
