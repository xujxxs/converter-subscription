package com.example.subscription.service.scheduledJob;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.subscription.event.Producer;
import com.example.subscription.exception.FreeSubscriptionNotFound;
import com.example.subscription.model.entity.Subscription;
import com.example.subscription.model.entity.UserSubscription;
import com.example.subscription.repository.SubscriptionRepository;
import com.example.subscription.repository.UserSubscriptionRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubscriptionScheduler {

    @Value("${queue.kafka.topic.subscription-del-cache}")
    private String SEND_TOPIC_DEL_CACHE;
    private final SubscriptionRepository subscriptionRepository;
    private final UserSubscriptionRepository userSubscriptionRepository;
    private final Producer producer;

    @Scheduled(fixedDelay = 5000)
    @Transactional
    public void checkEndSubscription() {
        Subscription sub = subscriptionRepository.findByName("FREE")
            .orElseThrow(() -> {
                log.error("Subscription 'FREE' not found");
                return new FreeSubscriptionNotFound();
            });

        List<UserSubscription> subscriptions = userSubscriptionRepository.findExpiredSubscriptions(
                LocalDateTime.now(), 
                PageRequest.of(0, 100)
            ).stream().map(userSub -> {
                userSub.setSubscription(sub);
                producer.sendMessage(
                    SEND_TOPIC_DEL_CACHE, 
                    UUID.randomUUID().toString(), 
                    userSub.getUsername());
                return userSub;
            }).toList();

        userSubscriptionRepository.saveAll(subscriptions);
    }
}
