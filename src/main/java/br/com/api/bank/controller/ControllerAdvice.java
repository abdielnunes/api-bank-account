package br.com.api.bank.controller;

import br.com.api.bank.exception.PreconditionFailedException;
import br.com.api.bank.exception.ResourceNotFoundException;
import br.com.api.bank.exception.MessageError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Void> handleResourceNotFoundException(ResourceNotFoundException notFoundException) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(PreconditionFailedException.class)
    public ResponseEntity<MessageError> handlePreconditionException(
            PreconditionFailedException preconditionFailedException) {
        return new ResponseEntity<>(preconditionFailedException.getErrors(), preconditionFailedException.getStatus());
    }
}
