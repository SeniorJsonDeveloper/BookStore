package dn.bookstore.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dn.bookstore.entity.BookEntity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserOutDto {

    public String id;

    private String username;

    private String password;

    private String email;

    private Double balance;

    private List<BookEntity> books = new ArrayList<>();
}
