package com.akulinski.subscriptiontrackerapi.core.repository;

import com.akulinski.subscriptiontrackerapi.core.domain.Subscription;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SubscriptionRepository extends CrudRepository<Subscription, UUID> {
  List<Subscription> findByPoster(String poster);
}
