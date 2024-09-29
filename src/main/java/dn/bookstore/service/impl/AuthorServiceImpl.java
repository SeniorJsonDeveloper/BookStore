package dn.bookstore.service.impl;

import dn.bookstore.entity.AuthorEntity;
import dn.bookstore.entity.BookEntity;
import dn.bookstore.repository.AuthorRepository;
import dn.bookstore.repository.BookRepository;
import dn.bookstore.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    private final BookRepository bookRepository;


    @Override
    public Map<String, List<AuthorEntity>> getAuthors() {
        List<AuthorEntity> authors = authorRepository.findAll();
        String key = authors.stream()
                .map(a->new AuthorEntity())
                .map(AuthorEntity::getBooks)
                .map(a->a.stream()
                        .map(BookEntity::getId)
                        .findFirst()
                        .toString())
                .filter(s -> !s.isEmpty())
                .findAny()
                .orElseThrow(RuntimeException::new);

        Map<String, List<AuthorEntity>> authorMap = new HashMap<>();
        authorMap.put(key,authors);
        return authorMap;
    }
}
