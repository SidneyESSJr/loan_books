package br.com.loan_books.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.loan_books.backend.domains.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitleContainingIgnoreCase(String title);

}
