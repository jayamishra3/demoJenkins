package com.learn.rest.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.learn.rest.exception.ContactExistException;
import com.learn.rest.exception.ContactNotFoundException;

import org.springframework.http.HttpStatus;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /* handler for exceptions */

    @ExceptionHandler(value = ContactNotFoundException.class)
    public ResponseEntity<String> contactNotFoundException(ContactNotFoundException contactNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(contactNotFoundException.getMessage());
    }

    @ExceptionHandler(value = ContactExistException.class)
    public ResponseEntity<String> contactExistException(ContactExistException contactExistException) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(contactExistException.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
    }

}
