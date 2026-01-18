package co.com.sofka.mscuentas.service.external.msclientes;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class MsClientesApiClientInterceptor implements RequestInterceptor {

    private final String authorizationHeader;

    public MsClientesApiClientInterceptor(String authorizationHeader) {
        this.authorizationHeader = authorizationHeader;
    }

    @Override
    public void apply(RequestTemplate template) {
        template.header("Authorization", authorizationHeader);
    }

}
