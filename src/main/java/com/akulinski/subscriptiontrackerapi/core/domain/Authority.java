package com.akulinski.subscriptiontrackerapi.core.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Audited
public class Authority implements GrantedAuthority {

  @Id @GeneratedValue private UUID uuid;

  private AuthorityType authorityType;

  @Override
  public String getAuthority() {
    return authorityType.name();
  }

  public void setAuthorityType(AuthorityType authorityType) {
    this.authorityType = authorityType;
  }

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
