package br.com.loan_books.backend.services.interfaces;

import java.util.List;

public interface DefaultCrud<T> {

    T save(T obj);

    T findById(Long id);

    List<T> findAll();

    T update(T obj);

    void delete(Long id);
}
