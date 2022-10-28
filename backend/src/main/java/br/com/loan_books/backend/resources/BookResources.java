package br.com.loan_books.backend.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.loan_books.backend.domains.Book;
import br.com.loan_books.backend.services.BookService;

@RestController
@RequestMapping("books")
public class BookResources {

    @Autowired
    private BookService service;

    @PostMapping
    public ResponseEntity<Book> save(@Valid @RequestBody Book book) {
        Book obj = service.save(book);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}").buildAndExpand(obj.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id) {
        Book book = service.findById(id);
        return ResponseEntity.ok().body(book);
    }

    @GetMapping
    public ResponseEntity<List<Book>> findAll() {
        List<Book> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/find-by-title")
    public ResponseEntity<List<Book>> findByTitle(@RequestParam(value = "title", defaultValue = "") String title) {
        List<Book> list = service.findByTitle(title);
        return ResponseEntity.ok().body(list);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @Valid @RequestBody Book book) {
        service.update(book);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
