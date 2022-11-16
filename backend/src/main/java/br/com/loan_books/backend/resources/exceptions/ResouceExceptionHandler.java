package br.com.loan_books.backend.resources.exceptions;

import java.time.LocalDateTime;

import org.hibernate.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ResouceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<?> ObjectNotFound(ObjectNotFoundException e, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandartError body = StandartError
                .builder()
                .timestamp(LocalDateTime.now())
                .status(status)
                .message(e.getMessage())
                .build();

        return handleExceptionInternal(e, body, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> DataIntegrityViolation(DataIntegrityViolationException e, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandartError body = StandartError
                .builder()
                .timestamp(LocalDateTime.now())
                .status(status)
                .message(e.getMessage())
                .build();

        return handleExceptionInternal(e, body, new HttpHeaders(), status, request);
    }
}
