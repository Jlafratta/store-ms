package edu.store.product.domain.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.math.BigDecimal;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
public class Product extends BaseEntity {

    @Column(length = 50)
    private String name;

    @Column(length = 100)
    private String description;

    @Column(nullable = false, precision = 19,scale = 2)
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
}
