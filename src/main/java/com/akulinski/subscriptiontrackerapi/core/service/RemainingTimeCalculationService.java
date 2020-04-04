package com.akulinski.subscriptiontrackerapi.core.service;

import com.akulinski.subscriptiontrackerapi.core.service.dto.SubscriptionDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
@Slf4j
public class RemainingTimeCalculationService {

  public int calculateRemainingDays(SubscriptionDTO subscriptionDTO) {

    final long diff = getDiff(subscriptionDTO);

    return Long.valueOf(diff).intValue();
  }

  private long getDiff(SubscriptionDTO subscriptionDTO) {

    var dueDate = subscriptionDTO.getDueDate();

    while (dueDate.isBefore(Instant.now())) {
      dueDate = dueDate.plus(subscriptionDTO.getPeriod(), ChronoUnit.DAYS);
    }

    subscriptionDTO.setDueDate(dueDate);

    return ChronoUnit.DAYS.between(Instant.now(), subscriptionDTO.getDueDate());
  }
}
