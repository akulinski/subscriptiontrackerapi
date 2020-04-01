package com.akulinski.subscriptiontrackerapi.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Data
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
}
