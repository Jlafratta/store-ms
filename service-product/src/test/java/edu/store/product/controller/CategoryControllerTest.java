package edu.store.product.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.store.product.domain.dto.CategoryDTO;
import edu.store.product.domain.model.Category;
import edu.store.product.service.CategoryService;
import edu.store.product.service.GenericService;
import edu.store.product.service.mocks.CategoryMock;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CategoryService categoryService;

    @MockBean
    CategoryController categoryController;

    CategoryMock categoryMock = new CategoryMock();

    ObjectMapper objectMapper = new ObjectMapper();


    @Test
    public void findAllTestOk() throws Exception {
        List<Category> categories = categoryMock.getList();

        doReturn(categories).when(categoryService).findAll(null, null);

        mockMvc.perform(
                get("/category").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void addTestOk() throws Exception {
        CategoryDTO request = CategoryDTO.builder().name("Premium").build();
        Category toSave = Category.builder()
                .name("Premium")
                .build();
        Category saved = Category.builder()
                .id(1L)
                .name("Premium")
                .build();
        URI url = ServletUriComponentsBuilder
                .fromPath("/category/{id}")
                .buildAndExpand(saved.getId())
                .toUri();

        when(categoryService.save(toSave)).thenReturn(saved);
        when(categoryController.getLocation(saved)).thenReturn(url);

        mockMvc.perform(
                post("/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}