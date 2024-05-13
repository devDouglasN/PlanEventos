package com.douglas.planeventos.services.exceptions.infra;

import com.douglas.planeventos.services.exceptions.ValidationException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handleError404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleError400(MethodArgumentNotValidException ex) {
        var errors = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream().map(DataValidationErrors::new).toList());
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity errorRegraDeNegocio(ValidationException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    private record DataValidationErrors(String field, String msg) {
        public DataValidationErrors(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
