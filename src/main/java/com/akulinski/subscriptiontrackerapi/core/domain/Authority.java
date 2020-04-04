package com.akulinski.subscriptiontrackerapi.core.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Audited
@EntityListeners(AuditingEntityListener.class)
public class Authority implements GrantedAuthority {

  @Id @GeneratedValue private UUID uuid;

  @Enumerated(EnumType.STRING)
  private AuthorityType authorityType;

  @Override
  public String getAuthority() {
    return authorityType.name();
  }

  public void setAuthorityType(AuthorityType authorityType) {
    this.authorityType = authorityType;
  }


  @CreatedDate
  private Instant created;

  @LastModifiedDate
  private Instant modified;

  @CreatedBy
  private String createdBy;

  @LastModifiedBy
  private String modifiedBy;
}
