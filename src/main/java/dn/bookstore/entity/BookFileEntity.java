package dn.bookstore.entity;

import dn.bookstore.repository.BookFileRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(schema = "bookstore_db",name = "book_files")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookFileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String hash;

    @Column(name = "type_id")
    private Integer typeId;

    private String path;

    @ManyToOne
    @JoinColumn(name = "book_id",referencedColumnName = "id")
    private BookEntity book;

    public String getBookFileExtentionString(){
        return FileType.getExtentionStringByTypeId(typeId);
    }

}
