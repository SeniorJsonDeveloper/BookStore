package dn.bookstore.service;

import dn.bookstore.exception.ApiResponseDto;
import dn.bookstore.entity.BookEntity;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

public interface BookService {

    List<BookEntity> getAllBooks();

    List<BookEntity> getBooksByAuthor(String author);

    List<BookEntity> getBooksWithPriceBetween(Double min, Double max);

    List<BookEntity> getBooksByTitle(String title);

    List<BookEntity> getBooksWithMaxPrice();

    List<BookEntity> getBookWithPrice(Double price);

    List<BookEntity> getBestsellers();

    Page<BookEntity> getPageOfRecommendedBooks(Integer offset,Integer limit);

    Page<BookEntity> getPageOfSearchResultBooks(String word,Integer offset,Integer limit);

    BookEntity getBookBySlug(String slug);

    String saveBookImage(MultipartFile file, String slug) throws IOException;

    Path getBookFilePath(String hash);

    byte[] getBookFileArray(String hash) throws IOException;

    MediaType getBookFileMime(String hash);

    Map<Object,ByteArrayResource> bookFileDownload(String hash) throws IOException;

    List<BookEntity> findBookEntityBySlugIn(String[] strings);

    ApiResponseDto<BookEntity> booksByTitle(String title);

    List<BookEntity> getPageOfGoogleBooks(String requireWord,Integer offset,Integer limit);

    RedirectView makePayment(String cookieValue) throws NoSuchAlgorithmException;

    String getPaymentUrl(List<BookEntity> books) throws NoSuchAlgorithmException;
}
