package com.example.subscription.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.subscription.exception.UsernameNotFound;
import com.example.subscription.model.enums.TypeSubscription;

@RestControllerAdvice
public class AdviceExceptionHandler {

    @ExceptionHandler(UsernameNotFound.class)
    public ResponseEntity<TypeSubscription> usernameNotFound(UsernameNotFound ex) {
        return ResponseEntity.ok(TypeSubscription.FREE);
    }
}
