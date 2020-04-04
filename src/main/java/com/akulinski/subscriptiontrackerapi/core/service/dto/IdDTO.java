package com.akulinski.subscriptiontrackerapi.core.service.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class IdDTO {
  private String id;

  public UUID getId() {
    return UUID.fromString(id);
  }
}
