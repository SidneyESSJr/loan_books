package br.com.loan_books.backend.services;

import static org.mockito.Mockito.when;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.com.loan_books.backend.domains.Author;
import br.com.loan_books.backend.resources.AuthorResources;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;

@SpringBootTest
public class AuthorResourceTest {

    @Autowired
    private AuthorResources resource;

    @MockBean
    private AuthorService service;

    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(this.resource);
    }

    @Test
    public void success_findAuthor() {

        when(this.service.findById(1L))
                .thenReturn(new Author(1L, "Sidney Junior", null));

        RestAssuredMockMvc.given()
                .accept(ContentType.JSON)
                .when().get("/authors/{id}", 1L)
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void success_save() {

    }

}
