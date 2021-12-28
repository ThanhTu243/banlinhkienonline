package com.thanhtu.crud.exception;

public class SelfDestructionExeption extends RuntimeException{
    public SelfDestructionExeption(String message) {
        super(message);
    }
}
