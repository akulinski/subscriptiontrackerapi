package com.akulinski.subscriptiontrackerapi.core.service;

import com.akulinski.subscriptiontrackerapi.core.domain.User;
import com.akulinski.subscriptiontrackerapi.core.repository.SubscriptionRepository;
import com.akulinski.subscriptiontrackerapi.core.repository.UserRepository;
import com.akulinski.subscriptiontrackerapi.core.service.dto.SubscriptionDTO;
import com.akulinski.subscriptiontrackerapi.core.service.mapper.SubscriptionMapper;
import com.akulinski.subscriptiontrackerapi.utils.SecurityWrapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Data
@Slf4j
@RequiredArgsConstructor
public class SubscriptionService {

  private final RemainingTimeCalculationService remainingTimeCalculationService;

  private final SubscriptionRepository subscriptionRepository;

  private final SubscriptionMapper subscriptionMapper;

  private final UserRepository userRepository;

  private final SecurityWrapper securityWrapper;

  public SubscriptionDTO save(SubscriptionDTO subscriptionDTO) {
    final var subscription = subscriptionMapper.asDO(subscriptionDTO);

    final var user = securityWrapper.getUser();

    subscription.setUser(user);

    final var savedSubscription =
        subscriptionMapper.asDTO(subscriptionRepository.save(subscription));

    savedSubscription.setPoster(user.getUsername());
    return savedSubscription;
  }

  public void saveAndGetUserFromDTO(SubscriptionDTO subscriptionDTO) {
    final var subscription = subscriptionMapper.asDO(subscriptionDTO);

    final var user = userRepository.findByUsername(subscriptionDTO.getPoster()).orElseThrow();

    subscription.setUser(user);

    final var savedSubscription =
        subscriptionMapper.asDTO(subscriptionRepository.save(subscription));

    savedSubscription.setPoster(user.getUsername());
  }

  public void deleteSubscription(UUID uuid) {
    subscriptionRepository.deleteById(uuid);
  }

  public List<SubscriptionDTO> findByPoster(User user) {

    final var byUser = subscriptionRepository.findByUser(user);

    return byUser.stream()
        .map(subscriptionMapper::asDTO)
        .peek(subscriptionDTO -> subscriptionDTO.setPoster(user.getUsername()))
        .peek(
            subscriptionDTO ->
                subscriptionDTO.setDaysLeft(
                    remainingTimeCalculationService.calculateRemainingDays(subscriptionDTO)))
        .collect(Collectors.toUnmodifiableList());
  }
}
