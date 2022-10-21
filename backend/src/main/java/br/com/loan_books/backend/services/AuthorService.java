package br.com.loan_books.backend.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.loan_books.backend.domains.Author;
import br.com.loan_books.backend.repositories.AuthorRepository;
import br.com.loan_books.backend.services.interfaces.DefaultCrud;

@Service
public class AuthorService implements DefaultCrud<Author> {

    @Autowired
    AuthorRepository repository;

    @Override
    public Author save(Author obj) {
        obj.setId(null);
        return repository.save(obj);
    }

    @Override
    public Author findById(Long id) {
        Optional<Author> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(id, Author.class.getName()));
    }

    @Override
    public List<Author> findAll() {
        return repository.findAll();
    }

    public List<Author> findByName(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public Author update(Author obj) {
        Author author = findById(obj.getId());
        BeanUtils.copyProperties(obj, author);
        return repository.save(author);
    }

    @Override
    public void delete(Long id) {
        findById(id);
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new DataIntegrityViolationException(
                    Author.class.getName() + "cannot be remover because it is linked to other entities");
        }
    }

}
