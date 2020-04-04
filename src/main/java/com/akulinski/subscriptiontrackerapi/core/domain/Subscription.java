package com.akulinski.subscriptiontrackerapi.core.domain;

import lombok.*;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity(name = "subscription")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Audited
@EntityListeners(AuditingEntityListener.class)
public class Subscription {

  @Id @GeneratedValue private UUID id;

  @Column private Instant instant;

  @Column private String siteName;

  @Column private BigDecimal bigDecimal;

  @Column private Integer period;

  @Column private Instant dueDate;

  @ManyToOne(fetch = FetchType.LAZY)
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  private User user;

  @CreatedDate private Instant created;

  @LastModifiedDate private Instant modified;

  @CreatedBy
  private String createdBy;

  @LastModifiedBy
  private String modifiedBy;
}
