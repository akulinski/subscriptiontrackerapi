package com.akulinski.subscriptiontrackerapi.web.v1;

import com.akulinski.subscriptiontrackerapi.core.service.SubscriptionService;
import com.akulinski.subscriptiontrackerapi.core.service.dto.SubscriptionDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/subscription")
@RestController
@Slf4j
@RequiredArgsConstructor
public class SubscriptionResource {
  private final SubscriptionService subscriptionService;

  @GetMapping("/{poster}")
  @ResponseStatus(HttpStatus.OK)
  public List<SubscriptionDTO> findBYPoster(@PathVariable String poster) {
    return subscriptionService.findByPoster(poster);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public SubscriptionDTO create(@RequestBody SubscriptionDTO subscriptionDTO) {
    return subscriptionService.save(subscriptionDTO);
  }
}
