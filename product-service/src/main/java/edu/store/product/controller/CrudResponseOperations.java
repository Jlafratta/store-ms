package edu.store.product.controller;

import edu.store.product.domain.model.BaseEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface CrudResponseOperations <T extends BaseEntity, ID> {
    ResponseEntity<T> add(T entity);
    ResponseEntity<T> add(T entity, BindingResult result);
    ResponseEntity<T> findById(ID id);
    ResponseEntity<List<T>> findAll(Integer page, Integer size);
    ResponseEntity<T> update(T entity);
    ResponseEntity<T> update(T entity, BindingResult result);
    ResponseEntity<T> delete(ID id);
    ResponseEntity<T> toggleEnabled(ID id);
}