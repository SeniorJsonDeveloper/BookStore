package dn.bookstore.repository;

import dn.bookstore.entity.BookFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookFileRepository extends JpaRepository<BookFileEntity, String> {

    BookFileEntity findByHash(String hash);
}