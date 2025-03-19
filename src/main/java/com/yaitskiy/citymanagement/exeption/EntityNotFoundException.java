package com.yaitskiy.citymanagement.exeption;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String entityName, Long entityId) {
        super(entityName + " with id " + entityId + " not found");
    }

}
