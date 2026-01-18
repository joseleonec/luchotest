package co.com.sofka.mscuentas.service.external.msclientes;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class ClienteFeignConfig {

    @Value("${api.msclientes.basic-auth.user}")
    public String msClientesUser;

    @Value("${api.msclientes.basic-auth.password}")
    public String msClientesPassword;

    @Bean
    public MsClientesApiClientInterceptor personasApiClientInterceptor() {

        String basicAuthHeader = "Basic " + Base64.getEncoder().encodeToString((msClientesUser + ":" + msClientesPassword).getBytes());

        return new MsClientesApiClientInterceptor(basicAuthHeader);
    }

    @Bean
    public MsClientesErrorDecoder personasErrorDecoder() {

        return new MsClientesErrorDecoder();
    }

}
