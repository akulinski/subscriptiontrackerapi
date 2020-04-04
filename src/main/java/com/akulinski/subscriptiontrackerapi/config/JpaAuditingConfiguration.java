package com.akulinski.subscriptiontrackerapi.config;

import com.akulinski.subscriptiontrackerapi.core.domain.User;
import com.akulinski.subscriptiontrackerapi.utils.SecurityWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
@EnableTransactionManagement
@RequiredArgsConstructor
@Slf4j
public class JpaAuditingConfiguration {

  private final SecurityWrapper securityWrapper;

  @Bean
  public AuditorAware<String> auditorProvider() {
    return () -> securityWrapper.getOptionalUser().map(User::getUsername);
  }
}
