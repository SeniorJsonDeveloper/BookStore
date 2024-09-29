package dn.bookstore.controller;

import dn.bookstore.dto.user.UserOutDto;
import dn.bookstore.dto.user.UserRegistryDto;
import dn.bookstore.entity.UserEntity;
import dn.bookstore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books/auth/my")
public class UserController {

    private final UserService userService;


    @PostMapping("/registry")
    @ResponseStatus(HttpStatus.CREATED)
    public UserEntity createAccount(@RequestBody UserRegistryDto userRegistryDto) {
       return userService.createUser(userRegistryDto);
    }
}
