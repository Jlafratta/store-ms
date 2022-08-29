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

import static org.junit.jupiter.api.Assertions.*;
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
    public void findAllPagedNullTestOk() {
        List<Category> categories = categoryMock.getList();
        Sort defaultSorting = Sort.by("modifyDate").descending();

        when(categoryRepository.findAll(defaultSorting)).thenReturn(categories);

        List<Category> categoriesResult = categoryService.findAll(0, null);

        assertEquals(categories.size(), categoriesResult.size());
        assertEquals(categories, categoriesResult);
        verify(categoryRepository, times(1)).findAll(defaultSorting);
    }

    @Test
    public void saveTestOk() {
        Category toSave = new Category();
        Category saved = categoryMock.getOne();
        toSave.setName(saved.getName());
        saved.setModifyDate(saved.getCreateDate());

        when(categoryRepository.save(toSave)).thenReturn(saved);

        Category result = categoryService.save(toSave);

        assertEquals(result.getId(), saved.getId());
        assertNotNull(result.getCreateDate());
        assertNotNull(result.getModifyDate());

        verify(categoryRepository, times(1)).save(toSave);
    }

    @Test
    public void deleteTestOk() {
        Long id = 1L;
        categoryService.delete(id);
        boolean result = categoryService.existsById(id);

        assertFalse(result);
    }

    @Test
    public void toggleEnabledTestOk() {
        Category category = categoryMock.getOne();

        when(categoryRepository.getById(category.getId())).thenReturn(category);

        category.setEnabled(!category.isEnabled());

        when(categoryService.save(category)).thenReturn(category);

        Category result = categoryService.toggleEnabled(category.getId());

        assertEquals(category.getId(), result.getId());
        assertTrue(result.isEnabled());

        verify(categoryRepository, times(1)).getById(category.getId());
    }

    @Test
    public void toggleDisabledTestOk() {
        Category category = categoryMock.getOne();
        category.setEnabled(false);

        when(categoryRepository.getById(category.getId())).thenReturn(category);

        category.setEnabled(!category.isEnabled());

        when(categoryService.save(category)).thenReturn(category);

        Category result = categoryService.toggleEnabled(category.getId());

        assertEquals(category.getId(), result.getId());
        assertFalse(result.isEnabled());

        verify(categoryRepository, times(1)).getById(category.getId());
    }
}