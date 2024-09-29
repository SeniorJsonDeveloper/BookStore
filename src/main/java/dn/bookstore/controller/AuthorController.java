package dn.bookstore.controller;

import dn.bookstore.entity.AuthorEntity;
import dn.bookstore.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @ModelAttribute("authorsList")
    @PreAuthorize("hasRole('ROLE_USER')")
    public Map<String, List<AuthorEntity>> authorsList() {
        return authorService.getAuthors();
    }

    @GetMapping("/authors")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String authors() {
        return "/auhtors/index";
    }

    @GetMapping("books/authors-with-books")
    @PreAuthorize("hasRole('ROLE_USER')")
    @ResponseBody
    public Map<String, List<AuthorEntity>> authorMap() {
        return authorService.getAuthors();
    }

}
