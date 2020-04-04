package com.akulinski.subscriptiontrackerapi.web.v1;

import com.akulinski.subscriptiontrackerapi.core.service.SubscriptionService;
import com.akulinski.subscriptiontrackerapi.core.service.dto.IdDTO;
import com.akulinski.subscriptiontrackerapi.core.service.dto.SubscriptionDTO;
import io.swagger.annotations.ApiImplicitParam;
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
  @ApiImplicitParam(
      name = "Authorization",
      value = "Access Token",
      required = true,
      paramType = "header",
      dataTypeClass = String.class,
      example = "Bearer access_token")
  public List<SubscriptionDTO> findBYPoster(@PathVariable String poster) {
    return subscriptionService.findByPoster(poster);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @ApiImplicitParam(
      name = "Authorization",
      value = "Access Token",
      required = true,
      paramType = "header",
      dataTypeClass = String.class,
      example = "Bearer access_token")
  public SubscriptionDTO create(@RequestBody SubscriptionDTO subscriptionDTO) {
    return subscriptionService.save(subscriptionDTO);
  }

  @DeleteMapping
  @ResponseStatus(HttpStatus.OK)
  @ApiImplicitParam(
      name = "Authorization",
      value = "Access Token",
      required = true,
      paramType = "header",
      dataTypeClass = String.class,
      example = "Bearer access_token")
  public void deleteById(@RequestBody IdDTO idDTO) {
    subscriptionService.deleteSubscription(idDTO.getId());
  }
}
