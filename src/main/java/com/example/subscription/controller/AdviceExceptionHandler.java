package com.example.subscription.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.subscription.exception.FreeSubscriptionNotFound;
import com.example.subscription.exception.UsernameNotFound;
import com.example.subscription.model.dto.SubscriptionTypeDto;
import com.example.subscription.model.entity.Subscription;
import com.example.subscription.repository.SubscriptionRepository;

import lombok.RequiredArgsConstructor;

@RestControllerAdvice
@RequiredArgsConstructor
public class AdviceExceptionHandler {

    private final SubscriptionRepository subscriptionRepository;

    @ExceptionHandler(UsernameNotFound.class)
    public ResponseEntity<SubscriptionTypeDto> usernameNotFound(UsernameNotFound ex) {
        Subscription sub = subscriptionRepository.findByName("FREE")
            .orElseThrow(() -> new FreeSubscriptionNotFound());
        return ResponseEntity.ok(new SubscriptionTypeDto(sub.getName(), sub.getFileLimit()));
    }

    @ExceptionHandler(FreeSubscriptionNotFound.class)
    public ResponseEntity<String> subscriptionNotFound(FreeSubscriptionNotFound ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ex.getMessage());
    }
}
