package com.thanhtu.crud.exception;

public class DuplicateRecoredException extends RuntimeException{
    public DuplicateRecoredException(String message) {
        super(message);
    }
}
