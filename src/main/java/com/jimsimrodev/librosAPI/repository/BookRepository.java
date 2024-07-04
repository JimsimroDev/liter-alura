package com.jimsimrodev.librosAPI.repository;

import com.jimsimrodev.librosAPI.models.Autor;
import com.jimsimrodev.librosAPI.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
 Optional<Book> findByTituloContainsIgnoreCase(String nameBook);

 @Query("SELECT a FROM Book l JOIN l.autores a ")
 List<Autor> mostrarAutores();
}
