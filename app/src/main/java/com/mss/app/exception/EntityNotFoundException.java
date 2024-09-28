package com.mss.app.exception;

/**
 * This is a custom exception class for taking care when entity is not found in DB.
 *
 * @author Prantik Sarkar
 */
public class EntityNotFoundException extends  RuntimeException{
    public EntityNotFoundException(String message) {
        super(message);
    }
}
