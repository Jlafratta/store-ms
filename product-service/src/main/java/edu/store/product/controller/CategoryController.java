package edu.store.product.controller;

import edu.store.product.domain.dto.CategoryDTO;
import edu.store.product.domain.dto.MappeableDTO;
import edu.store.product.domain.model.Category;
import edu.store.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController extends GenericController<Category, Long> {

    @Autowired
    public CategoryController(CategoryService categoryService) {
        service = categoryService;
    }

    @PostMapping()
    public ResponseEntity<Category> add(@RequestBody @Valid CategoryDTO entity, BindingResult result) {
        return super.add(map(entity), result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id) {
        return super.findById(id);
    }

    @GetMapping()
    public ResponseEntity<List<Category>> findAll(
            @RequestParam(name = "page", required = false) Integer page,
            @RequestParam(name = "size", required = false) Integer size) {
        return super.findAll(page, size);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody CategoryDTO entity) {
        return super.update(map(entity, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> delete(@PathVariable Long id) {
        return super.delete(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Category> toggleEnabled(@PathVariable Long id) {
        return super.toggleEnabled(id);
    }

    @Override
    public URI getLocation(Category entity) {
        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/category/{id}")
                .buildAndExpand(entity.getId())
                .toUri();
    }

    @Override
    protected Category map(MappeableDTO dto) {
        return mapper.map(dto, Category.class);
    }

    @Override
    protected Category map(MappeableDTO dto, Long id) {
        Category cat = map(dto);
        cat.setId(id);
        return cat;
    }
}
