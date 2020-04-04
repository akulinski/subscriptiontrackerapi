package com.akulinski.subscriptiontrackerapi.core.repository;

import com.akulinski.subscriptiontrackerapi.core.domain.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, UUID> {
  List<Subscription> findByUser_Username(String poster);
}
