package dn.bookstore.dto.google;

import dn.bookstore.entity.BookEntity;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookFileDto {

    private String id;

    private String hash;

    private Integer typeId;

    private String path;

    private BookEntity book;
}
