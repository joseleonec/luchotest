package co.com.sofka.mscuentas.service.external.msclientes;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.NoSuchElementException;

import feign.Response;
import feign.codec.ErrorDecoder;

public class MsClientesErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {

        switch (response.status()) {
            case 404 -> {
                return new NoSuchElementException("La persona no existe (404)");
            }
            case 500 -> {
                String responseBody;
                try {
                    responseBody = response.body().asInputStream().readAllBytes().length > 0
                            ? new String(response.body().asInputStream().readAllBytes(), StandardCharsets.UTF_8)
                            : "No hay detalles del error";
                } catch (IOException e) {
                    responseBody = "No se pudo leer el cuerpo de la respuesta";
                }
                return new RuntimeException("Error interno en el microservicio de personas (500): " + responseBody);
            }
            case 503 -> {
                return new RuntimeException("El microservicio de personas no está disponible (503)");
            }
            case 502 -> {
                return new RuntimeException("Error en la comunicación con el microservicio de personas (502)");
            }
            default -> {
                return defaultErrorDecoder.decode(methodKey, response);
            }
        }
    }
}
