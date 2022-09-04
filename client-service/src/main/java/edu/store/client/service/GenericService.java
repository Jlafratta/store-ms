package edu.store.client.service;

import edu.store.client.domain.model.BaseEntity;
import edu.store.client.exception.EntityNotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

public abstract class GenericService<T extends BaseEntity, ID> {

    protected JpaRepository<T, ID> repository;

    public T save(T entity) {
        return repository.save(entity);
    }

    public T findById(ID id) {
        return findByIdOptional(id)
                .orElseThrow(() -> new EntityNotFoundException(id.toString()));
    }

    public Optional<T> findByIdOptional(ID id) {
        return repository.findById(id);
    }

    public List<T> findAll(Integer page, Integer size) {
        return !isNull(page) && !isNull(size) ?
                repository.findAll(PageRequest.of(page, size, defaultSorting())).getContent() :
                repository.findAll(defaultSorting());
    }

    public T update(T entity) {
        checkExists((ID) entity.getId());
        return save(entity);
    }

    public void delete(ID id) {
        checkExists(id);
        repository.deleteById(id);
    }

    private void checkExists(ID id) {
        if(!repository.existsById(id)) throw new EntityNotFoundException(id.toString());
    }

    public T toggleEnabled(ID id) {
        checkExists(id);
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
