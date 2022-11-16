package br.com.loan_books.backend.domains.emuns;

public enum TypeCard {

    CREDIT(1),
    DEBIT(2);

    private int value;

    TypeCard(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
