package br.com.loan_books.backend.domains;

import java.util.HashSet;
import java.util.Set;

public class Client {

    private Long id;

    private String name;

    private String cpf;

    private Set<Card> cards = new HashSet<>();
}
