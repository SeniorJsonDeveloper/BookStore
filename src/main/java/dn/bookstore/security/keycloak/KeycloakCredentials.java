package dn.bookstore.security.keycloak;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ConfigurationProperties(prefix = "spring.keycloak.client")
@Component
public class KeycloakCredentials {

    private String id;

    private String secret;
}
