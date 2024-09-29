package dn.bookstore.security.keycloak;

public interface KeycloakClient {

    String getToken(String clientId,String clientSecret);
}
