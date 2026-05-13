package com.example.subscription.model.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_subscription")
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSubscription {

    @Id
    private String username;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subscription_id", nullable = false)
    private Subscription subscription;
    
    @Column(name = "expires", nullable = false)
    private LocalDateTime expires;

    @Override
    public boolean equals(Object anObject) {
        if(this == anObject) return true;
        if(anObject == null || getClass() != anObject.getClass()) return false;

        return ((UserSubscription) anObject).getUsername().equals(this.username);
    }

    @Override
    public int hashCode() {
        return this.username.hashCode();
    }
}
