package com.akulinski.subscriptiontrackerapi.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity(name = "subscription")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Subscription {

  @Id @GeneratedValue private UUID id;

  @Column private Instant instant;

  @Column private String siteName;

  @Column private BigDecimal bigDecimal;

  @Column private Integer period;

  @Column private String poster;
}
