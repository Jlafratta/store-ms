package edu.store.product.exception;

import lombok.Getter;

import java.util.List;

@Getter
public class ValidationException extends RuntimeException {
    private List<String> errors;

    public ValidationException(List<String> errors) {
        super();
        this.errors = errors;
    }
}
