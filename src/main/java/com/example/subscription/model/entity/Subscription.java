package com.example.subscription.model.entity;

import java.time.LocalDateTime;

import com.example.subscription.model.enums.TypeSubscription;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "subscription")
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Subscription {

    @Id
    private String username;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private TypeSubscription type;
    
    @Column(name = "expires", nullable = false)
    private LocalDateTime expires;

    @Override
    public boolean equals(Object anObject) {
        if(this == anObject) return true;
        if(anObject == null || getClass() != anObject.getClass()) return false;

        return ((Subscription) anObject).getUsername().equals(this.username);
    }

    @Override
    public int hashCode() {
        return this.username.hashCode();
    }
}
