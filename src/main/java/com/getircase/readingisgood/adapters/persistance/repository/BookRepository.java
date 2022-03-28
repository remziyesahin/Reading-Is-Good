package com.getircase.readingisgood.adapters.persistance.repository;

import com.getircase.readingisgood.adapters.persistance.entity.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, String> {
}
