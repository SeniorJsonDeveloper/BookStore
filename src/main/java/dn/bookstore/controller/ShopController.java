package dn.bookstore.controller;

import dn.bookstore.dto.SearchWordDto;
import dn.bookstore.dto.google.BookPageDto;
import dn.bookstore.entity.BookEntity;
import dn.bookstore.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ShopController {

    private final BookService bookService;


    @ModelAttribute("recommendedBooks")
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<BookEntity> recommendedBooks() {
        return bookService.getPageOfRecommendedBooks(0, 6).getContent();
    }

    @ModelAttribute("searchResult")
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<BookEntity> searchResult() {
        return new ArrayList<>();
    }

    @GetMapping("/")
    public String mainPage(){
        return "index";
    }

    @GetMapping("/books/recommended")
    @PreAuthorize("hasRole('ROLE_USER')")
    public BookPageDto getBooksPage(@RequestParam Integer offset,
                                    @RequestParam Integer limit){
        return new BookPageDto(bookService.getPageOfRecommendedBooks(offset, limit).getContent());
    }

    @GetMapping("/search/page/{requireWord}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public BookPageDto getBookPage(@RequestParam("offset") Integer offset,
                                   @RequestParam("limit") Integer limit,
                                   @PathVariable(value = "requireWord", required = false)
                                   SearchWordDto searchWordDto){
        return new BookPageDto(bookService.getPageOfGoogleBooks(searchWordDto.getWord(),offset,limit));
    }

}
