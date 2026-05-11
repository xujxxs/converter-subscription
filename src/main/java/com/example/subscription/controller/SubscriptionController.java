package com.example.subscription.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.subscription.model.enums.TypeSubscription;
import com.example.subscription.service.SubscriptionService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@RequestMapping("/api/v1/subscription")
@RestController
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @GetMapping("/status")
    public ResponseEntity<TypeSubscription> getMethodName(
        @RequestHeader("X-User-Login") String username
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(subscriptionService.getStatus(username));
    }
}
