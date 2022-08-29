package edu.store.product.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String id) {
        super("Entity with id " + id + " not found.");
    }

    public EntityNotFoundException(String entityName, String id) {
        super(entityName + " with id " + id + " not found.");
    }
}
