package com.example.subscription.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.subscription.model.entity.UserSubscription;

public interface UserSubscriptionRepository extends JpaRepository<UserSubscription, String> {

    @Query("SELECT s FROM UserSubscription s JOIN FETCH s.subscription WHERE s.username = :username")
    Optional<UserSubscription> findByIdWithFetchSubscription(String username);

    @EntityGraph(attributePaths = {"subscription"})
    @Query("SELECT s FROM UserSubscription s WHERE s.subscription.name != 'FREE' AND s.expires < :time")
    List<UserSubscription> findExpiredSubscriptions(
        @Param("time") LocalDateTime time, 
        Pageable page
    );
}