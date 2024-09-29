package dn.bookstore.repository;

import dn.bookstore.entity.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, String> {

  List<BookEntity> findBookEntityByAuthorFirstName(String name);

  List<BookEntity> findBookEntityByAuthorFirstNameContaining(String name);

  List<BookEntity> findBookEntityByTitleContaining(String bookTitle);


  List<BookEntity> findBookEntityByOldPriceBetween(Double oldPrice, Double oldPrice2);

  List<BookEntity> findBookEntityByOldPriceIs(Double oldPrice);

  @Query(value = "SELECT * FROM bookstore_db.books WHERE is_bestseller=1 ",nativeQuery = true)
  List<BookEntity> getBestSellers();

  @Query(value = "SELECT * FROM bookstore_db.books WHERE discount = (SELECT MAX(discount) FROM books",nativeQuery = true)
  List<BookEntity> getWithMaxDiscounts();

  Page<BookEntity> findBookEntityByTitleContaining(String bookTitle, Pageable pageable);

  BookEntity findBookEntityBySlug(String slug);

  List<BookEntity> findBookEntityBySlugIn(String[] slugs);



}