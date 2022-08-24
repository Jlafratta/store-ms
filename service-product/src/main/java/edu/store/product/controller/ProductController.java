package edu.store.product.controller;

import edu.store.product.domain.model.Product;
import edu.store.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController extends GenericController <Product, Long> {

    @Autowired
    public ProductController(ProductService productService) {
        service = productService;
    }

    @PostMapping
    public ResponseEntity<Product> add(@RequestBody Product entity) {
        return super.add(entity); //TODO parametrizar con dto y mappear
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        return super.findById(id);
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll(
            @RequestParam(name = "page", required = false) Integer page,
            @RequestParam(name = "size", required = false)Integer size) {
        return super.findAll(page, size);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product entity) {
        return super.update(entity); //TODO parametrizar dto y mappear
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> delete(@PathVariable Long id) {
        return super.delete(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Product> toggleEnabled(@PathVariable Long id) {
        return super.toggleEnabled(id);
    }

    @Override
    protected URI getLocation(Product entity) {
        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/product/{id}")
                .buildAndExpand(entity.getId())
                .toUri();
    }
}
