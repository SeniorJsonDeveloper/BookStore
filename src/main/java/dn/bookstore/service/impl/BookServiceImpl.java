package dn.bookstore.service.impl;

import dn.bookstore.exception.ApiResponseDto;
import dn.bookstore.dto.google.Item;
import dn.bookstore.dto.google.Root;
import dn.bookstore.entity.AuthorEntity;
import dn.bookstore.entity.BookEntity;
import dn.bookstore.entity.BookFileEntity;
import dn.bookstore.repository.BookFileRepository;
import dn.bookstore.repository.BookRepository;
import dn.bookstore.service.BookService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClient;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    @Value("${integration.google.books.api.key}")
    private String API_KEY;

    private final RestClient restClient;

    @Value("${integration.payments.robokassa.merchant.login}")
    private String merchantLogin;

    @Value("${integration.payments.robokassa.merchant.testPassword}")
    private String merchantPassword;

    private final BookFileRepository bookFileRepository;

    private final BookRepository bookRepository;


    @Override
    @Cacheable("books")
    public List<BookEntity> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    @Cacheable("books")
    public List<BookEntity> getBooksByAuthor(String author) {
        return bookRepository.findBookEntityByAuthorFirstName(author);
    }

    @Override
    public List<BookEntity> getBooksWithPriceBetween(Double min, Double max) {
        return bookRepository.findBookEntityByOldPriceBetween(min, max);
    }

    @Override
    @Cacheable("books")
    public List<BookEntity> getBooksByTitle(String title) {
        if (title.length() <= 1) {
            throw new RuntimeException("Не найдено");
        } else {
            List<BookEntity> books = bookRepository.findBookEntityByTitleContaining(title);
            if (!books.isEmpty()) {
                return books;
            } else {
                throw new RuntimeException("Не найдено");
            }
        }
    }

    @Override
    @Cacheable("books")
    public List<BookEntity> getBooksWithMaxPrice() {
        return bookRepository.getWithMaxDiscounts();
    }

    @Override
    @Cacheable("books")
    public List<BookEntity> getBookWithPrice(Double price) {
        return bookRepository.findBookEntityByOldPriceIs(price);
    }

    @Override
    @Cacheable("books")
    public List<BookEntity> getBestsellers() {
        return bookRepository.getBestSellers();
    }

    @Override
    @Cacheable("books")
    public Page<BookEntity> getPageOfRecommendedBooks(Integer offset, Integer limit) {
        Pageable pageable = PageRequest.of(offset, limit);
        return bookRepository.findAll(pageable);
    }

    @Override
    @Cacheable("books")
    public Page<BookEntity> getPageOfSearchResultBooks(String word, Integer offset, Integer limit) {
        Pageable pageable = PageRequest.of(offset, limit);
        return bookRepository.findBookEntityByTitleContaining(word, pageable);
    }

    @Override
    @Cacheable("books")
    public BookEntity getBookBySlug(String slug) {
        return bookRepository.findBookEntityBySlug(slug);
    }


    @Value("${file.upload.path}")
    private String fileUploadPath;

    @Value("${file.download.downloadPath}")
    private String fileDownloadPath;


    @Override
    @Transactional
    public String saveBookImage(MultipartFile file, String slug) throws IOException {
        String resourceURI = null;
        if (!file.isEmpty()) {
            if (new File(fileUploadPath).exists()) {
                Files.createDirectories(Paths.get(fileUploadPath));
            }
            String fileName = slug+"."+ FilenameUtils.getExtension(file.getOriginalFilename());
            Path path = Paths.get(fileUploadPath, fileName);
            resourceURI = "/book-covers"+fileName;
            file.transferTo(path);
            String savePath = saveBookImage(file,slug);
            BookEntity book = bookRepository.findBookEntityBySlug(slug);
            book.setImage(savePath);
            bookRepository.save(book);
            return resourceURI;
        }
        return "redirect:/books/"+slug;
    }

    @Override
    public Path getBookFilePath(String hash) {
        BookFileEntity bookFileEntity = bookFileRepository.findByHash(hash);
        return Paths.get(bookFileEntity.getPath());
    }

    @Override
    public byte[] getBookFileArray(String hash) throws IOException {
        BookFileEntity bookFileEntity = bookFileRepository.findByHash(hash);
        Path path = Paths.get(fileDownloadPath,bookFileEntity.getPath());
        return Files.readAllBytes(path);
    }

    @Override
    public MediaType getBookFileMime(String hash) {
        BookFileEntity bookFileEntity = bookFileRepository.findByHash(hash);
        String mimeType = URLConnection.guessContentTypeFromName(
                Paths.get(bookFileEntity.getPath()).getFileName().toString());
        if (mimeType!=null){
            return MediaType.parseMediaType(mimeType);
        }
        else {
            return MediaType.APPLICATION_OCTET_STREAM;
        }
    }


    @Override
    public Map<Object,ByteArrayResource> bookFileDownload(String hash) throws IOException {
        Path path = getBookFilePath(hash);
        MediaType mediaType = getBookFileMime(hash);
        byte[] data = getBookFileArray(hash);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(mediaType);
        httpHeaders.setContentLength(data.length);
        httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename"+path.getFileName());
        ByteArrayResource byteArrayResource = new ByteArrayResource(data);
        Map<Object,ByteArrayResource> map = new HashMap<>();
        map.put(httpHeaders,byteArrayResource);
        if(map.containsKey(null)){
            throw new RuntimeException(""); //TODO:
        }

        if(!map.containsValue(byteArrayResource)|| !Arrays.equals(byteArrayResource.getByteArray(), data)){
            throw new RuntimeException(""); //TODO:
        }

        return map;


    }

    @Override
    @Cacheable("books")
    public List<BookEntity> findBookEntityBySlugIn(String[] strings) {
        return bookRepository.findBookEntityBySlugIn(strings);
    }

    @Override
    @Cacheable("books")
    public ApiResponseDto<BookEntity> booksByTitle(String title) {
        ApiResponseDto<BookEntity> apiResponseDto = new ApiResponseDto<>();
        List<BookEntity> bookEntities = getBooksByTitle(title);
        apiResponseDto.setDebugMessage("successful request");
        apiResponseDto.setMessage("gooooooood!");
        apiResponseDto.setData(bookEntities);
        apiResponseDto.setHttpStatus(HttpStatus.OK);
        apiResponseDto.setTimestamp(LocalDateTime.now());
        apiResponseDto.setData(bookEntities);
        return apiResponseDto;


    }

    @Override
    public List<BookEntity> getPageOfGoogleBooks(String requireWord, Integer offset, Integer limit) {
        String REQUEST_URL = "https://www.googleapis.com/books/v1/volumes" +
                "?q=" + requireWord +
                "&key=" + API_KEY +
                "&filter=paid-ebooks" +
                "&startIndex=" + offset +
                "&maxResult=" + limit;
        Root root = restClient.get()
                .uri(REQUEST_URL)
                .retrieve()
                .body(Root.class);
        List<BookEntity> books = new ArrayList<>();
        if (root != null) {
            for (Item item : root.getItems()) {
                BookEntity book = new BookEntity();
                if (item.getVolumeInfo() != null) {
                    book.setAuthor(new AuthorEntity(item.getVolumeInfo().getAuthors()));//TODO
                    book.setTitle(item.getVolumeInfo().getTitle());
                    book.setImage(item.getVolumeInfo().getImageLinks().getThumbnail());
                }
                if (item.getSaleInfo() != null) {
                    book.setPrice(BigDecimal.valueOf(item.getSaleInfo().getRetailPrice().getAmount()));
                    double oldPrice = item.getSaleInfo().getListPrice().getAmount();
                    book.setOldPrice((Double) oldPrice);
                }
                books.add(book);
            }
        }
        return books;
    }

    @Override
    public RedirectView makePayment(String cartContents) throws NoSuchAlgorithmException {

      cartContents = cartContents.startsWith("/") ? cartContents.substring(1) : cartContents;
      cartContents = cartContents.endsWith("/") ? cartContents.substring(0, cartContents.length() - 1) : cartContents;
      String[] cookieSlugs = cartContents.split("/");
      List<BookEntity> booksFromCookieSlugs = bookRepository.findBookEntityBySlugIn(cookieSlugs);
      String paymentUrl = getPaymentUrl(booksFromCookieSlugs);
      return new RedirectView(paymentUrl);

    }

    @Override
    public String getPaymentUrl(List<BookEntity> books) throws NoSuchAlgorithmException {
        double paymentSum = books.stream().mapToDouble(BookEntity::getOldPrice).sum();
        MessageDigest messageDigest = MessageDigest.getInstance("SHA512");
        String invId = "5";
        messageDigest.update((merchantLogin+":"+ paymentSum +":"+invId+":"+merchantPassword).getBytes());
        return "https://auth.robokassa.ru/Merchant/Index.aspx"+
                "?MerchantLogin"+merchantLogin+
                "InvId"+invId+
                "&Culture=ru"+
                "&Encoding=UTF-8"+
                "OutSum"+paymentSum+
                "&SignatureValue="+ DatatypeConverter.printHexBinary(messageDigest.digest()).toUpperCase();


    }


}































