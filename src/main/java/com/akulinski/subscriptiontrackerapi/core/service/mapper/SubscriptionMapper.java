package com.akulinski.subscriptiontrackerapi.core.service.mapper;

import com.akulinski.subscriptiontrackerapi.core.domain.Subscription;
import com.akulinski.subscriptiontrackerapi.core.service.dto.SubscriptionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.WARN)
public interface SubscriptionMapper {
  SubscriptionDTO asDTO(Subscription subscription);

  Subscription asDO(SubscriptionDTO subscriptionDTO);
}
