package edu.store.product.service;

import edu.store.product.domain.model.Category;
import edu.store.product.repository.CategoryRepository;
import edu.store.product.service.mocks.CategoryMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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

    @Test
    public void findAllPagedTestOk() {
        List<Category> categories = categoryMock.getList();
        Sort defaultSorting = Sort.by("modifyDate").descending();
        Integer page = 0;
        Integer size = 3;
        PageRequest request = PageRequest.of(page, size, defaultSorting);
        Page<Category> response = new PageImpl(categories);

        when(categoryRepository.findAll(request)).thenReturn(response);

        List<Category> result = categoryService.findAll(page, size);

        assertEquals(categories.size(), result.size());
        assertEquals(categories, result);
        verify(categoryRepository, times(1)).findAll(request);
    }

    @Test
    void findAllPagedNullTestOk() {
        List<Category> categories = categoryMock.getList();
        Sort defaultSorting = Sort.by("modifyDate").descending();

        when(categoryRepository.findAll(defaultSorting)).thenReturn(categories);

        List<Category> categoriesResult = categoryService.findAll(0, null);

        assertEquals(categories.size(), categoriesResult.size());
        assertEquals(categories, categoriesResult);
        verify(categoryRepository, times(1)).findAll(defaultSorting);
    }
}