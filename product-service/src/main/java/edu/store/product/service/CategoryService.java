package edu.store.product.service;

import edu.store.product.domain.model.Category;
import edu.store.product.exception.EntityNotFoundException;
import edu.store.product.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService extends GenericService<Category, Long>{

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        repository = categoryRepository;
    }

    @Override
    public Category findById(Long id) {
        return super.findByIdOptional(id)
                .orElseThrow(() -> new EntityNotFoundException(Category.class.getSimpleName(), id.toString()));
    }
}
