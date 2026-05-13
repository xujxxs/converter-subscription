package com.example.subscription.exception;

public class UsernameNotFound extends RuntimeException {

    public UsernameNotFound(String username) {
        super("Error username: " + username + " not found");
    }
}
