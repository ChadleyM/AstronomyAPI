package com.group8.stargaming.Exceptions;

public class ConstellationNotFoundException extends RuntimeException {
    
    public ConstellationNotFoundException(String name) {
        super("Could not find constellation: " + name);
    }
}
