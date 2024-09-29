package dn.bookstore.repository;

import dn.bookstore.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,String> {

    Boolean existsByUsername(String username);

    Boolean existsByUsernameAndEmail(String username, String password);

    @Override
    void deleteAll();
}
