package edu.store.product.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class ProductDTO implements MappeableDTO {
    @NotEmpty
    @Length(max = 50)
    private String name;
    @NotEmpty
    @Length(max = 100)
    private String description;
    @NotNull
    private BigDecimal price;
    private BasicIdDTO category;
}
