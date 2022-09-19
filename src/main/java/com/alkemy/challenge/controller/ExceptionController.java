package com.alkemy.challenge.controller;

import com.alkemy.challenge.dto.ApiErrorDTO;
import com.alkemy.challenge.exception.Duplicate;
import com.alkemy.challenge.exception.ParamNotFound;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        Map<String,String> errores= new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error ->{
            String fieldName = ((FieldError) error).getField();
            String mensaje=error.getDefaultMessage();
            errores.put(fieldName,mensaje);
        });
        return new ResponseEntity<Object>(errores,HttpStatus.BAD_REQUEST);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ParamNotFound.class)
    protected ResponseEntity<Object> handleParamNotFound(RuntimeException ex, WebRequest request){

        ApiErrorDTO errorDTO = new ApiErrorDTO(
                HttpStatus.BAD_REQUEST,ex.getMessage(), Arrays.asList("Param Not Found")
        );

        return handleExceptionInternal(ex,errorDTO, new HttpHeaders(),HttpStatus.BAD_REQUEST,request);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Duplicate.class)
    protected ResponseEntity<Object> handleDuplicate(RuntimeException ex, WebRequest request){

        ApiErrorDTO errorDTO = new ApiErrorDTO(
                HttpStatus.BAD_REQUEST,ex.getMessage(), Arrays.asList("Duplicate")
        );

        return handleExceptionInternal(ex,errorDTO, new HttpHeaders(),HttpStatus.BAD_REQUEST,request);
    }


}
