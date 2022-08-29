package edu.store.product.domain.dto;

import edu.store.product.domain.enumeration.ErrorTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter @Setter
public class ErrorResponseDTO {
    private int code;
    private ErrorTypeEnum errorType;
    private List<String> errors;
}
