package com.akulinski.subscriptiontrackerapi.core.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Entity
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
}
