package com.alkemy.challenge.exception;

public class ParamNotFound extends RuntimeException{
    public ParamNotFound(){}

    public ParamNotFound(String message) {
        super(message);
    }
}
