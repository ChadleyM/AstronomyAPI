package com.group8.stargaming.Exceptions;

public class ConstellationNotFoundException extends RuntimeException {
    
    public ConstellationNotFoundException(Long id) {
        super("Could not find constellation " + id);
    }
}
