package edu.store.product.service.mocks;

import edu.store.product.domain.model.Category;
import edu.store.product.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryMock {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category getOne() {
        return Category.builder()
                .id(1L)
                .name("Premium")
                .build();
    }

    public List<Category> getList() {
        return List.of(
                Category.builder()
                        .id(1L)
                        .name("Premium")
                        .build(),
                Category.builder()
                        .id(2L)
                        .name("Regular")
                        .build(),
                Category.builder()
                        .id(3L)
                        .name("LowCost")
                        .build()
        );
    }

    public void init() {
        categoryRepository.saveAll(getList());
    }
}
