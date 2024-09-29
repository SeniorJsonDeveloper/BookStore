package dn.bookstore.dto.google.books;

import dn.bookstore.entity.BookEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class BookPageDto {

    private Integer count;

    private List<BookEntity> books;

    public BookPageDto(List<BookEntity> books) {
        this.count = books.size();
        this.books = books;
    }

    public BookPageDto(Integer count, List<BookEntity> books) {
        this.count = count;
        this.books = books;
    }
}
