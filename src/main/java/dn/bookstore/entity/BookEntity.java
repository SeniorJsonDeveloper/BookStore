package dn.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(schema = "bookstore_db",name = "books")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private Date publishDate;

    @Column(name = "is_bestseller")
    private Integer isBestseller;

    private String title;

    @Column(name = "discount")
    private BigDecimal price;

    private String image;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Double oldPrice;

    private String slug;

    @ManyToOne
    @JoinColumn(name = "author_id",referencedColumnName = "id")
    private AuthorEntity author;

//    @ManyToOne
//    @JoinColumn(name = "user_id",referencedColumnName = "id")
//    private UserEntity user;



    @OneToMany(mappedBy = "book")
    private List<BookFileEntity> bookFiles;


    public String getAuthorFullNames(){
        return author.toString();
    }
}
