package org.example.exception;

public class CheckGenerateException extends RuntimeException {
    public CheckGenerateException(Exception e) {
        super(e);
    }
}
