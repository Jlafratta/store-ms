package edu.store.product.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class ErrorResponseDTO {
    private int code;
    private String message;
}
