package com.example.subscription.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.subscription.model.entity.Subscription;
import com.example.subscription.model.enums.TypeSubscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, String> {

    @Query("SELECT s FROM Subscription s WHERE s.type = :type AND s.expires < :time")
    List<Subscription> findExpiredSubscriptions(
        @Param("type") TypeSubscription type,
        @Param("time") LocalDateTime time, 
        Pageable page
    );
}
