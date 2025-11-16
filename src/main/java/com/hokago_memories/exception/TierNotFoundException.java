package com.hokago_memories.exception;

public class TierNotFoundException extends IllegalArgumentException {

    public TierNotFoundException(String message) {
        super(message);
    }
}
