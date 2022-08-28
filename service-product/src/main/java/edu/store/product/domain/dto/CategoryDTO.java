package edu.store.product.domain.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CategoryDTO implements MappeableDTO {
    private String name;
}
