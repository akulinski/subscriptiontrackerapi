package com.akulinski.subscriptiontrackerapi.core.domain;

import lombok.*;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Entity(name = "subscription")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Audited
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

  @Column(name = "created_date")
  @CreatedDate
  private Date createdDate;

  @Column(name = "modified_date")
  @LastModifiedDate
  private Date modifiedDate;

  @Column(name = "created_by")
  @CreatedBy
  private String createdBy;

  @Column(name = "modified_by")
  @LastModifiedBy
  private String modifiedBy;
}
