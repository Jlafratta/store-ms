package edu.store.product.domain.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
public class Category extends BaseEntity {

    @Column(length = 50)
    private String name;

    public Category withId(Long id) {
        setId(id);
        return this;
    }
}
