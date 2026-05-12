package com.example.subscription.service;

import org.springframework.stereotype.Service;

import com.example.subscription.exception.UsernameNotFound;
import com.example.subscription.model.dto.SubscriptionTypeDto;
import com.example.subscription.model.entity.UserSubscription;
import com.example.subscription.repository.UserSubscriptionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final UserSubscriptionRepository userSubscriptionRepository;

    public SubscriptionTypeDto getSubscriptionType(String username) {
        UserSubscription userSubscription = userSubscriptionRepository.findByIdWithFetchSubscription(username)
            .orElseThrow(() -> new UsernameNotFound(username));

        return new SubscriptionTypeDto(
            userSubscription.getSubscription().getName(), 
            userSubscription.getSubscription().getFileLimit());
    }
}
