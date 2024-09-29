package dn.bookstore.security.keycloak.impl;

import dn.bookstore.dto.UserRegistryDto;
import dn.bookstore.entity.UserEntity;
import dn.bookstore.exception.AlreadyExistsException;
import dn.bookstore.repository.UserRepository;
import dn.bookstore.security.keycloak.UserClient;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserClientImpl  implements UserClient {

    private static final String ROLE_USER = "ROLE_USER";

    private final Keycloak keycloak;

    private final UserRepository userRepository;

    @Value("${spring.keycloak.realm}")
    private String realm;

    @Override
    public UserEntity createUser(UserRegistryDto userRegistryDto) {
        RealmResource realmResource = keycloak.realm(realm);

        UserRepresentation userRepresentation = createUserRepresentation(userRegistryDto);
        setCredentials(userRepresentation, userRegistryDto.getPassword());
        var response = realmResource.users().create(userRepresentation);
        String userId = CreatedResponseUtil.getCreatedId(response);
        addRole(realmResource, userId);
        return new UserEntity(userId,userRegistryDto.getUsername(),userRegistryDto.getPassword());
    }

    public void addRole(RealmResource realmResource,String userId) {
        UserResource userResource = realmResource.users().get(userId);
        var role = keycloak.realm(realm).roles().get(ROLE_USER).toRepresentation();
        userResource.roles().realmLevel().add(List.of(role));
    }

    public void setCredentials(UserRepresentation userRepresentation,String password){
        CredentialRepresentation representation = new CredentialRepresentation();
        representation.setTemporary(false);
        representation.setType(CredentialRepresentation.PASSWORD);
        representation.setValue(password);
        userRepresentation.setCredentials(List.of(representation));
        userRepresentation.setRealmRoles(List.of(ROLE_USER));
    }
    public UserRepresentation createUserRepresentation(UserRegistryDto userRegistryDto) {
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setUsername(userRegistryDto.getUsername());

        if (userRepository.existsByUsername(userRegistryDto.getUsername())) {
            throw new AlreadyExistsException
                    (MessageFormat.format("User with username : {0} already exists",
                            userRegistryDto.getUsername()));
        }
        userRepresentation.setEmail(userRegistryDto.getEmail());

        if (userRepository.existsByUsernameAndEmail(userRegistryDto.getUsername(), userRegistryDto.getEmail())) {
            throw new AlreadyExistsException
                    (MessageFormat.format("User with username or email :{0} : {0} already exists",
                            userRegistryDto.getUsername()));
        }
        userRepresentation.setEmailVerified(true);
        userRepresentation.setEnabled(true);
        return userRepresentation;
    }















}
