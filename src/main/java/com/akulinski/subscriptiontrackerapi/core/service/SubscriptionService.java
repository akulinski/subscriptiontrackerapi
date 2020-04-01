package com.akulinski.subscriptiontrackerapi.core.service;

import com.akulinski.subscriptiontrackerapi.core.repository.SubscriptionRepository;
import com.akulinski.subscriptiontrackerapi.core.repository.UserRepository;
import com.akulinski.subscriptiontrackerapi.core.service.dto.SubscriptionDTO;
import com.akulinski.subscriptiontrackerapi.core.service.mapper.SubscriptionMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
@Slf4j
@RequiredArgsConstructor
public class SubscriptionService {

  private final SubscriptionRepository subscriptionRepository;

  private final SubscriptionMapper subscriptionMapper;

  private final UserRepository userRepository;

  public SubscriptionDTO save(SubscriptionDTO subscriptionDTO) {
    final var subscription = subscriptionMapper.asDO(subscriptionDTO);
    final var user =
        userRepository
            .findByUsername(subscriptionDTO.getPoster())
            .orElseThrow(
                () ->
                    new IllegalStateException(
                        "No User found by id: " + subscriptionDTO.getPoster()));

    subscription.setUser(user);

    return subscriptionMapper.asDTO(subscriptionRepository.save(subscription));
  }

  public List<SubscriptionDTO> findByPoster(String poster) {
    final var byPoster = subscriptionRepository.findByUser_Username(poster);

    return byPoster.stream()
        .map(subscriptionMapper::asDTO)
        .collect(Collectors.toUnmodifiableList());
  }
}
