package edu.store.product.service;

import edu.store.product.domain.model.Category;
import edu.store.product.repository.CategoryRepository;
import edu.store.product.service.mocks.CategoryMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CategoryServiceTest {

    CategoryService categoryService;
    CategoryMock categoryMock;

    @Mock
    CategoryRepository categoryRepository;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        categoryMock = new CategoryMock();
        categoryService = new CategoryService(categoryRepository);
    }

    @Test
    public void findAllTestOk() {
        List<Category> categories = categoryMock.getList();
        Sort defaultSorting = Sort.by("modifyDate").descending();

        when(categoryRepository.findAll(defaultSorting)).thenReturn(categories);

        List<Category> categoriesResult = categoryService.findAll(null, null);

        assertEquals(categories.size(), categoriesResult.size());
        assertEquals(categories, categoriesResult);
        verify(categoryRepository, times(1)).findAll(defaultSorting);
    }
}