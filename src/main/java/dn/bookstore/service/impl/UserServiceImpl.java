package dn.bookstore.service.impl;

import dn.bookstore.dto.user.UserOutDto;
import dn.bookstore.dto.user.UserRegistryDto;
import dn.bookstore.entity.UserEntity;
import dn.bookstore.exception.AlreadyExistsException;
import dn.bookstore.mapper.UserMapper;
//import dn.bookstore.repository.UserRepository;
import dn.bookstore.repository.UserRepository;
import dn.bookstore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private static final String ROLE_USER = "ROLE_USER";

    private final Keycloak keycloak;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Value("${spring.keycloak.realm}")
    private String realm;

    @Override
    @Transactional
    public UserEntity createUser(UserRegistryDto userRegistryDto) {
        RealmResource realmResource = keycloak.realm(realm);
        UserRepresentation userRepresentation = createUserRepresentation(userRegistryDto);
        String encodePassword = passwordEncoder.encode(userRegistryDto.getPassword());
        setCredentials(userRepresentation,encodePassword);
        var response = realmResource.users().create(userRepresentation);
        String userId = CreatedResponseUtil.getCreatedId(response);
        addRole(realmResource, userId);
        UserEntity savedUser = new UserEntity(userId,
                userRegistryDto.getUsername(),
                encodePassword,
                userRegistryDto.getEmail());

        return userRepository.save(savedUser);

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

//        if (userRepository.existsByUsername(userRegistryDto.getUsername())) {
//            throw new AlreadyExistsException
//                    (MessageFormat.format("User with username : {0} already exists",
//                            userRegistryDto.getUsername()));
//        }
        userRepresentation.setEmail(userRegistryDto.getEmail());

//        if (userRepository.existsByUsernameAndEmail(userRegistryDto.getUsername(), userRegistryDto.getEmail())) {
//            throw new AlreadyExistsException
//                    (MessageFormat.format("User with username or email :{0} : {0} already exists",
//                            userRegistryDto.getUsername()));
//        }
        userRepresentation.setEmailVerified(true);
        userRepresentation.setEnabled(true);
        return userRepresentation;
    }















}
