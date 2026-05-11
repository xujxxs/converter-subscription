package com.example.subscription.service.scheduledJob;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.subscription.model.entity.Subscription;
import com.example.subscription.model.enums.TypeSubscription;
import com.example.subscription.repository.SubscriptionRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubscriptionScheduler {

    private final SubscriptionRepository subscriptionRepository;
    private final RedisTemplate<String, Object> redisTemplate;

    @Scheduled(fixedDelay = 5000)
    @Transactional
    public void checkEndSubscription() {
        List<Subscription> subscriptions = subscriptionRepository.findExpiredSubscriptions(
                TypeSubscription.PAID,
                LocalDateTime.now(), 
                PageRequest.of(0, 100)
            ).stream().map(sub -> {
                sub.setType(TypeSubscription.FREE);
                redisTemplate.delete("type_subscription::" + sub.getUsername());
                return sub;
            }).toList();

        subscriptionRepository.saveAll(subscriptions);
    }
}
