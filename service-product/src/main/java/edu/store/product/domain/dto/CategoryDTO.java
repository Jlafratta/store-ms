package edu.store.product.domain.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
public class CategoryDTO implements MappeableDTO {
    @NotEmpty
    private String name;
}