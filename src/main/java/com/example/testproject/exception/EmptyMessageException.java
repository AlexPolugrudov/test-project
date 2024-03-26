package com.example.testproject.exception;

public class EmptyMessageException extends RuntimeException {
    public EmptyMessageException(String message) {
        super(message);
    }
}
