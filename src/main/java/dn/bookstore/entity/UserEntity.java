package dn.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "bookstore_db",name = "users")
@Getter
@Setter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public String id;

    private String username;

    private String password;

    private String email;

    private Double balance;

//    @OneToMany(mappedBy = "user")
//    @JsonIgnore
//    private List<BookEntity> books = new ArrayList<>();

    public UserEntity(String userId, String username, String password,String email) {
        this.username = username;
        this.password = password;
        this.id = userId;
        this.email = email;

    }


}
