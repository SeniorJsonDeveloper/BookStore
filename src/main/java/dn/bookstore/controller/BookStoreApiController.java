package dn.bookstore.controller;

import dn.bookstore.exception.ApiResponseDto;
import dn.bookstore.entity.BookEntity;
import dn.bookstore.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/bookStore/books")
@RequiredArgsConstructor
public class BookStoreApiController {

    private final BookService bookService;

    @GetMapping("/by-author")
    @ResponseStatus(HttpStatus.OK)
    public List<BookEntity> getBookByAuthor(@RequestParam String author) {
        return bookService.getBooksByAuthor(author);
    }

    @GetMapping("/by-title")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponseDto<BookEntity> getBookByTitle(@RequestParam String title) {
        return bookService.booksByTitle(title);
    }

    @GetMapping("/by-price-range")
    @ResponseStatus(HttpStatus.OK)
    public List<BookEntity> getBooksByRange(@RequestParam Double min,
                                            @RequestParam Double max) {
        return bookService.getBooksWithPriceBetween(min, max);
    }

    @GetMapping("/bestsellers")
    @ResponseStatus(HttpStatus.OK)
    public List<BookEntity> getBestsellers() {
        return bookService.getBestsellers();
    }




}
