package com.alkemy.challenge.exception;

public class Duplicate extends RuntimeException{
    public Duplicate(){}

    public Duplicate(String message) {
        super(message);
    }
}
