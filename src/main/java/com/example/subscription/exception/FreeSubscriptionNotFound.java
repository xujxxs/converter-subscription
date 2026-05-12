package com.example.subscription.exception;

public class FreeSubscriptionNotFound extends RuntimeException {

    public FreeSubscriptionNotFound() {
        super("Subscription: 'FREE' not found");
    }
}
