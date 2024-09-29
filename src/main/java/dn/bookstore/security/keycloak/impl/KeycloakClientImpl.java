package dn.bookstore.security.keycloak.impl;

import dn.bookstore.security.keycloak.KeycloakClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@RequiredArgsConstructor
@Component
public class KeycloakClientImpl implements KeycloakClient {


    private static final String CLIENT_ID = "client_id";

    private static final String CLIENT_SECRET = "client_secret";

    private static final String GRANT_TYPE = "grant_type";

    private static final String CLIENT_CREDENTIALS = "client_credentials";

    @Value("${spring.keycloak.token-url}")
    private String tokenUrl;

    private final RestClient restClient;


    @Override
    public String getToken(String clientId, String clientSecret) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add(CLIENT_ID, clientId);
        params.add(CLIENT_SECRET, clientSecret);
        params.add(GRANT_TYPE, CLIENT_CREDENTIALS);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String,String>> request = new HttpEntity<>(params, httpHeaders);
        var response = restClient.post()
                              .uri(tokenUrl)
                              .body(request)
                              .retrieve()
                              .toEntity(String.class);
        return Objects.requireNonNull(response).getBody();

    }
}
