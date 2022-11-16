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

import br.com.loan_books.backend.domains.Author;
import br.com.loan_books.backend.services.AuthorService;

@RestController
@RequestMapping(value = "/authors")
public class AuthorResources {

    @Autowired
    AuthorService service;

    @PostMapping
    public ResponseEntity<Author> save(@Valid @RequestBody Author author) {
        Author obj = service.save(author);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Author> findById(@PathVariable Long id) {
        Author author = service.findById(id);
        return ResponseEntity.ok().body(author);
    }

    @GetMapping(value = "/find-by-name")
    public ResponseEntity<List<Author>> findByName(@RequestParam(value = "name", defaultValue = "") String name) {
        List<Author> list = service.findByName(name);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping
    public ResponseEntity<List<Author>> findAll() {
        List<Author> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @Valid @RequestBody Author author) {
        author.setId(id);
        service.update(author);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
