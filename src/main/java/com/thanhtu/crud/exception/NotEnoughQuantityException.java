package com.thanhtu.crud.exception;

public class NotEnoughQuantityException extends RuntimeException{
    public NotEnoughQuantityException(String message) {
        super(message);
    }
}
