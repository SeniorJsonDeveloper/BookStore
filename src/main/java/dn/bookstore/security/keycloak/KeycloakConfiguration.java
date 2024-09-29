package dn.bookstore.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.OAuth2Constants;

@Configuration
@EnableConfigurationProperties(KeycloakCredentials.class)
public class KeycloakConfiguration {

    @Bean
    public Keycloak keycloakConfig(@Value("${spring.keycloak.url}") String url,
                                   @Value("${spring.keycloak.realm}")String realm,
                                   KeycloakCredentials keycloakCredentials){
        return KeycloakBuilder.builder()
                .serverUrl(url)
                .realm(realm)
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .clientId(keycloakCredentials.getId())
                .clientSecret(keycloakCredentials.getSecret())
                .build();
    }
}
