package edu.store.product.controller;

import edu.store.product.domain.dto.ErrorResponseDTO;
import edu.store.product.domain.enumeration.ErrorTypeEnum;
import edu.store.product.exception.EntityNotFoundException;
import edu.store.product.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@RestControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ErrorResponseDTO handleEntityNotFoundException(EntityNotFoundException e) {
        return new ErrorResponseDTO(HttpStatus.NOT_FOUND.value(), ErrorTypeEnum.SERVER, List.of(e.getMessage()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public ErrorResponseDTO handleValidationException(ValidationException e) {
        return new ErrorResponseDTO(HttpStatus.BAD_REQUEST.value(), ErrorTypeEnum.VALIDATION, e.getErrors());
    }
}
