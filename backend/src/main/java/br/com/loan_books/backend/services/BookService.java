package br.com.loan_books.backend.services;

import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.loan_books.backend.domains.Book;
import br.com.loan_books.backend.repositories.BookRepository;
import br.com.loan_books.backend.services.interfaces.DefaultCrud;

@Service
public class BookService implements DefaultCrud<Book> {

    @Autowired
    private BookRepository repository;

    @Override
    public Book save(Book obj) {
        obj.setId(null);
        return repository.save(obj);
    }

    @Override
    public Book findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, Book.class.getName()));
    }

    @Override
    public List<Book> findAll() {
        return repository.findAll();
    }

    public List<Book> findByTitle(String title) {
        return repository.findByTitleContainingIgnoreCase(title);
    }

    @Override
    public void update(Book obj) {
        Book book = findById(obj.getId());
        BeanUtils.copyProperties(obj, book, "id");
        repository.save(book);
    }

    @Override
    public void delete(Long id) {
        findById(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("cannot be remove because it is linked to other entities");
        }
    }

}
