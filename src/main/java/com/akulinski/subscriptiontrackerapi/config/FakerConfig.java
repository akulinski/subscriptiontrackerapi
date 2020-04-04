package com.akulinski.subscriptiontrackerapi.config;

import com.akulinski.subscriptiontrackerapi.core.service.SubscriptionService;
import com.akulinski.subscriptiontrackerapi.core.service.UserService;
import com.akulinski.subscriptiontrackerapi.core.service.dto.SubscriptionDTO;
import com.akulinski.subscriptiontrackerapi.core.service.dto.UserDTO;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;

import java.math.BigDecimal;
import java.util.stream.Stream;

@Configuration
@Profile("faker")
@RequiredArgsConstructor
@Slf4j
public class FakerConfig {

  private final UserService userService;

  private final SubscriptionService subscriptionService;

  private final Faker faker = Faker.instance();

  @EventListener(ApplicationReadyEvent.class)
  public void init() {
    createUser();

    Stream.generate(
            () -> {
              SubscriptionDTO subscriptionDTO = new SubscriptionDTO();
              subscriptionDTO.setPeriod(faker.random().nextInt(1000));
              subscriptionDTO.setPoster("user");
              subscriptionDTO.setSiteName(faker.internet().domainName());
              subscriptionDTO.setDueDate(faker.date().birthday().toInstant());
              subscriptionDTO.setPrice(BigDecimal.valueOf(faker.random().nextInt(1000)));
              return subscriptionDTO;
            })
        .limit(10)
        .forEach(subscriptionService::saveAndGetUserFromDTO);
  }

  private void createUser() {
    UserDTO userDTO = new UserDTO();
    userDTO.setUsername("user");
    userDTO.setPassword("user");
    userService.create(userDTO);
  }
}
