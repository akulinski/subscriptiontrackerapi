package com.akulinski.subscriptiontrackerapi.core.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
  private UUID id;

  private String username;

  private String password;
}
