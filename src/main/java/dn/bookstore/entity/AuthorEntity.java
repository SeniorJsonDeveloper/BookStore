package dn.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "bookstore_db",name = "authors")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuthorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String firstName;

    private String lastName;

    @OneToMany(mappedBy = "author")
    @JsonIgnore
    private List<BookEntity> books = new ArrayList<>();


    public AuthorEntity(List<String> authors) {
        if (authors != null) {
            this.firstName = authors.toString();
        }
    }
}
