package com.akulinski.subscriptiontrackerapi.core.service;

import com.akulinski.subscriptiontrackerapi.PostgresqlContainerExtension;
import com.akulinski.subscriptiontrackerapi.core.service.dto.SubscriptionDTO;
import com.akulinski.subscriptiontrackerapi.core.service.dto.UserDTO;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.stream.Collectors;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.yaml")
@ExtendWith(PostgresqlContainerExtension.class)
class SubscriptionServiceIntTest {

  @Autowired private UserService userService;

  @Autowired private SubscriptionService subscriptionService;

  @BeforeEach
  void setUp() {}

  @Test
  void save() {
    UserDTO userDTO = new UserDTO();
    userDTO.setUsername("user");
    userDTO.setPassword("user");

    final var userDTO1 = userService.create(userDTO);

    SubscriptionDTO subscriptionDTO = new SubscriptionDTO();
    subscriptionDTO.setPoster("user");

    final var subscriptionDTO1 = subscriptionService.save(subscriptionDTO);

    final var byPoster = subscriptionService.findByPoster("user");

    final var subscriptionDTOList =
        byPoster.stream()
            .filter(subscriptionDTO2 -> "user".equals(subscriptionDTO2.getPoster()))
            .collect(Collectors.toUnmodifiableList());

    Assert.assertEquals(1, subscriptionDTOList.size());
  }
}
