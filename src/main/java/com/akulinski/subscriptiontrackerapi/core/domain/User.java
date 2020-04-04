package com.akulinski.subscriptiontrackerapi.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.AbstractAuditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity(name = "app_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Audited
@EntityListeners(AuditingEntityListener.class)
public class User implements UserDetails {

  @Id @GeneratedValue private UUID id;

  @Column private String passwordHash;

  @Column(unique = true, updatable = false, nullable = false)
  private String username;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Set<Subscription> subscriptions;

  @NotEmpty
  @NotNull
  @JsonIgnore
  @OneToMany
  @Fetch(value = FetchMode.JOIN)
  private Set<Authority> authorities;

  @Override
  @JsonIgnore
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return this.passwordHash;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public String toString() {
    return "User{"
        + "id="
        + id
        + ", passwordHash='"
        + passwordHash
        + '\''
        + ", username='"
        + username
        + '\''
        + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof User)) return false;
    User user = (User) o;
    return Objects.equals(getId(), user.getId())
        && Objects.equals(getPasswordHash(), user.getPasswordHash())
        && Objects.equals(getUsername(), user.getUsername());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getPasswordHash(), getUsername());
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
