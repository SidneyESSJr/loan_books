package br.com.loan_books.backend.resources.exceptions;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StandartError implements Serializable {

    private LocalDateTime timestamp;
    private HttpStatus status;
    private String message;
}
