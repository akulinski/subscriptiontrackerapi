package com.akulinski.subscriptiontrackerapi.core.service;

import com.akulinski.subscriptiontrackerapi.core.repository.SubscriptionRepository;
import com.akulinski.subscriptiontrackerapi.core.repository.UserRepository;
import com.akulinski.subscriptiontrackerapi.core.service.dto.SubscriptionDTO;
import com.akulinski.subscriptiontrackerapi.core.service.dto.UserDTO;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
class SubscriptionServiceIntTest {

  @Autowired private SubscriptionRepository subscriptionRepository;

  @Autowired private UserRepository userRepository;

  @Autowired private UserService userService;

  @Autowired private SubscriptionService subscriptionService;

  @BeforeEach
  void setUp() {

    subscriptionRepository.deleteAll();

    userRepository.deleteAll();
  }

  @Test
  void save() {
    UserDTO userDTO = new UserDTO();
    userDTO.setUsername("user");
    userDTO.setPassword("user");

    userService.create(userDTO);

    SubscriptionDTO subscriptionDTO = new SubscriptionDTO();
    subscriptionDTO.setPoster("user");

    subscriptionService.save(subscriptionDTO);

    final var byPoster = subscriptionService.findByPoster(userRepository.findByUsername("user").get());

    final var subscriptionDTOList =
        byPoster.stream()
            .filter(subscriptionDTO2 -> "user".equals(subscriptionDTO2.getPoster()))
            .collect(Collectors.toUnmodifiableList());

    Assert.assertEquals(1, subscriptionDTOList.size());
  }

  @Test
  void findAll() {

    UserDTO userDTO = new UserDTO();
    userDTO.setUsername("user");
    userDTO.setPassword("user");
    userService.create(userDTO);

    Stream.generate(
            () -> {
              SubscriptionDTO subscriptionDTO = new SubscriptionDTO();
              subscriptionDTO.setPoster("user");
              return subscriptionDTO;
            })
        .limit(10)
        .forEach(subscriptionService::save);

    final var byPoster = subscriptionService.findByPoster(userRepository.findByUsername("user").get());

    final var subscriptionDTOList =
        byPoster.stream()
            .filter(subscriptionDTO2 -> "user".equals(subscriptionDTO2.getPoster()))
            .collect(Collectors.toUnmodifiableList());

    Assert.assertEquals(10, subscriptionDTOList.size());
  }
}
