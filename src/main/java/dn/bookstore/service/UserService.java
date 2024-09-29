package dn.bookstore.security.keycloak;

import dn.bookstore.dto.UserRegistryDto;
import dn.bookstore.entity.UserEntity;

public interface UserClient {

    UserEntity createUser(UserRegistryDto userRegistryDto);

}
