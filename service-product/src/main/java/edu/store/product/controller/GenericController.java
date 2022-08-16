package edu.store.product.controller;

import edu.store.product.domain.model.BaseEntity;
import edu.store.product.service.GenericService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.List;

public abstract class GenericController <T extends BaseEntity, ID> implements CrudResponseOperations <T, ID> {

    protected final ModelMapper mapper = new ModelMapper();
    protected GenericService<T, ID> service;

    protected abstract URI getLocation(T entity);

    @Override
    public ResponseEntity<T> add(T entity) {
        return ResponseEntity.created(getLocation(service.save(entity))).build();
    }

    @Override
    public ResponseEntity<List<T>> findAll(Integer page, Integer size) {
        return ResponseEntity.ok(service.findAll(page, size));
    }

    @Override
    public ResponseEntity<T> update(T entity) {
        checkExists((ID) entity.getId());
        return ResponseEntity.ok(service.save(entity));
    }

    @Override
    public ResponseEntity<T> delete(ID id) {
        checkExists(id);
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<T> toggleEnabled(ID id) {
        checkExists(id);
        return ResponseEntity.ok(service.toggleEnabled(id));
    }

    private void checkExists(ID id) {
        if(!service.existsById(id)) throw new RuntimeException("Entity with id "+id+" not found"); //TODO pasar a custom exceptions;
    }
}
