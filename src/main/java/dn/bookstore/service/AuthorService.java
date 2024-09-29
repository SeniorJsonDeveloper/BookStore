package dn.bookstore.service;

import dn.bookstore.entity.AuthorEntity;

import java.util.List;
import java.util.Map;

public interface AuthorService {

    Map<String, List<AuthorEntity>> getAuthors();
}
