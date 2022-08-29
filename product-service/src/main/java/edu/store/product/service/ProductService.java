package edu.store.product.service;

import edu.store.product.domain.model.Product;
import edu.store.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends GenericService <Product, Long> {

    @Autowired
    public ProductService(ProductRepository productRepository) {
        repository = productRepository;
    }
}
