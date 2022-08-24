package edu.store.product.controller;

import edu.store.product.domain.model.BaseEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CrudResponseOperations <T extends BaseEntity, ID> {
    ResponseEntity<T> add(T entity);
    ResponseEntity<T> findById(ID id);
    ResponseEntity<List<T>> findAll(Integer page, Integer size);
    ResponseEntity<T> update(T entity);
    ResponseEntity<T> delete(ID id);
    ResponseEntity<T> toggleEnabled(ID id);
}