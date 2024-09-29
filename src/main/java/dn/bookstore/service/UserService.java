package dn.bookstore.service;

import dn.bookstore.dto.user.UserOutDto;
import dn.bookstore.dto.user.UserRegistryDto;
import dn.bookstore.entity.UserEntity;

public interface UserService {

    UserEntity createUser(UserRegistryDto userRegistryDto);

}
