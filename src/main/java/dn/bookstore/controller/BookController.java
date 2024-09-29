package dn.bookstore.controller;

import dn.bookstore.entity.BookEntity;
import dn.bookstore.service.BookService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;


    @ModelAttribute("bookCart")
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<BookEntity> bookCarts() {
        return new ArrayList<>();
    }

    @GetMapping("/{slug}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String bookPage(@PathVariable("slug") String slug, Model model) {
        bookService.getBookBySlug(slug);
        model.addAttribute("slugBook",slug);
        return "/books/slug";
    }

    @PostMapping("/{slug}/img/save")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String saveNewBookImage(@RequestParam MultipartFile file,
                                   @PathVariable String slug) throws IOException {
        return bookService.saveBookImage(file,slug);
    }

    @GetMapping("/download/{hash}")
    public Map<Object,ByteArrayResource> downloadBookImage(@PathVariable String hash) throws IOException {
       return bookService.bookFileDownload(hash);
    }



    @GetMapping("/cart")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String handleCartRequest(@CookieValue(value = "cartContents", required = false) String cartContents,
                                    Model model) {
        if (cartContents == null || cartContents.equals("")) {
            model.addAttribute("isCartEmpty", true);
        } else {
            model.addAttribute("isCartEmpty", false);
            cartContents = cartContents.startsWith("/") ? cartContents.substring(1) : cartContents;
            cartContents = cartContents.endsWith("/") ? cartContents.substring(0, cartContents.length() - 1) :
                    cartContents;
            String[] cookieSlugs = cartContents.split("/");
            List<BookEntity> booksFromCookieSlugs = bookService.findBookEntityBySlugIn(cookieSlugs);
            model.addAttribute("bookCart", booksFromCookieSlugs);
        }

        return "cart";
    }

    @PostMapping("/changeBookStatus/cart/remove/{slug}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String handleRemoveBookFromCartRequest(@PathVariable("slug") String slug, @CookieValue(name = "cartContents", required = false) String cartContents,
                                                  HttpServletResponse response,
                                                  Model model) {
        if (cartContents != null && !cartContents.equals("")) {
            ArrayList<String> cookieBooks = new ArrayList<>(Arrays.asList(cartContents.split("/")));
            cookieBooks.remove(slug);
            Cookie cookie = new Cookie("cartContents", String.join("/", cookieBooks));
            cookie.setPath("/books");
            response.addCookie(cookie);
            model.addAttribute("isCartEmpty", false);
        } else {
            model.addAttribute("isCartEmpty", true);
        }

        return "redirect:/books/cart";
    }

    @PostMapping("/changeBookStatus/{slug}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String handleChangeBookStatus(@PathVariable("slug") String slug, @CookieValue(name = "cartContents",
            required = false) String cartContents, HttpServletResponse response, Model model) {

        if (cartContents == null || cartContents.isEmpty()) {
            Cookie cookie = new Cookie("cartContents", slug);
            cookie.setPath("/books");
            response.addCookie(cookie);
            model.addAttribute("isCartEmpty", false);
        } else if (!cartContents.contains(slug)) {
            StringJoiner stringJoiner = new StringJoiner("/");
            stringJoiner.add(cartContents).add(slug);
            Cookie cookie = new Cookie("cartContents", stringJoiner.toString());
            cookie.setPath("/book");
            response.addCookie(cookie);
            model.addAttribute("isCartEmpty", false);
        }

        return "books/" + slug;
    }



    


}
