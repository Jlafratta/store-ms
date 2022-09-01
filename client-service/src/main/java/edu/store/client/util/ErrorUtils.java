package edu.store.client.util;

import edu.store.client.exception.ValidationException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.stream.Collectors;

public final class ErrorUtils {

    public static List<String> formatFieldErrors(List<FieldError> fieldErrors) {
        return fieldErrors.stream()
                .map(ErrorUtils::formattedError)
                .collect(Collectors.toList());
    }

    private static String formattedError(FieldError e) {
        return e.getField().substring(0, 1).toUpperCase() + e.getField().substring(1) + ": " + e.getDefaultMessage();
    }

    public static void processFieldErrors(BindingResult result) {
        if (result.hasFieldErrors()) {
            throw new ValidationException(ErrorUtils.formatFieldErrors(result.getFieldErrors()));
        }
    }
}
