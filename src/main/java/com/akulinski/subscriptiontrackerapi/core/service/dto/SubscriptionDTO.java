package com.akulinski.subscriptiontrackerapi.core.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionDTO {

  private UUID id;

  private Instant dueDate;

  private BigDecimal price;

  private String siteName;

  private Integer period;

  private Integer daysLeft;

  private String poster;
}
