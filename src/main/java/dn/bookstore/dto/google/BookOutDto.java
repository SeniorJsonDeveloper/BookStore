package dn.bookstore.dto.google.books;

import com.fasterxml.jackson.annotation.JsonProperty;
import dn.bookstore.entity.BookEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class BookOutDto {

    private Integer count;

    private List<BookEntity> books;

    private String id;

    private Date publishDate;

    @JsonProperty("is_BestSeller")
    private Integer isBestseller;

    private String title;

    private BigDecimal price;

    private String image;

    private String description;

    private BigDecimal oldPrice;

    private String slug;

    private List<BookFileDto> bookFiles;

    public BookOutDto(List<BookEntity> books) {
        this.count = books.size();
        this.books = books;
    }

    public BookOutDto(Integer count, List<BookEntity> books) {
        this.count = count;
        this.books = books;
    }

    public BookOutDto(Integer count,
                      String id,
                      Date publishDate,
                      Integer isBestseller,
                      String title,
                      BigDecimal price,
                      String image,
                      String description,
                      BigDecimal oldPrice,
                      String slug,
                      List<BookFileDto> bookFiles) {
        this.count = count;
        this.id = id;
        this.publishDate = publishDate;
        this.isBestseller = isBestseller;
        this.title = title;
        this.price = price;
        this.image = image;
        this.description = description;
        this.oldPrice = oldPrice;
        this.slug = slug;
        this.bookFiles = bookFiles;
    }
}
