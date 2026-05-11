package com.example.subscription.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.subscription.exception.UsernameNotFound;
import com.example.subscription.model.enums.TypeSubscription;
import com.example.subscription.repository.SubscriptionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    @Cacheable(value = "type_subscription", key = "#username")
    public TypeSubscription getStatus(String username) {
        return subscriptionRepository.findById(username)
            .orElseThrow(() -> new UsernameNotFound(username)).getType();
    }
}
