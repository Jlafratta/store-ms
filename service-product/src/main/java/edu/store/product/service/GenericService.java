package edu.store.product.service;

import edu.store.product.domain.model.BaseEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import static java.util.Objects.isNull;

public class GenericService<T extends BaseEntity, ID> {

    protected JpaRepository<T, ID> repository;

    public T save(T entity) {
        return repository.save(entity);
    }

    public List<T> findAll(Integer page, Integer size) {
        return !isNull(page) || !isNull(size) ?
                repository.findAll(PageRequest.of(page, size, defaultSorting())).getContent() :
                repository.findAll(defaultSorting());
    }

    public void delete(ID id) {
        repository.deleteById(id);
    }

    public T toggleEnabled(ID id) {
        T entity = repository.getById(id);
        entity.setEnabled(!entity.isEnabled());
        return save(entity);
    }

    public boolean existsById(ID id) {
        return repository.existsById(id);
    }

    private Sort defaultSorting() {
        return Sort.by("modifyDate").descending();
    }
}
