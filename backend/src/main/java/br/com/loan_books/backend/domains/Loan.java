package br.com.loan_books.backend.domains;

import java.time.LocalDate;

public class Loan {

    private Long id;

    private LocalDate startDate;

    private LocalDate endDate;

    private Double cost;

    private Double total;

    private Boolean status;
}
