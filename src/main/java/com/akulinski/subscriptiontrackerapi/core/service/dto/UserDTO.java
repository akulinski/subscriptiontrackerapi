package com.akulinski.subscriptiontrackerapi.core.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {

  @Size(min = 0)
  private UUID id;

  @Size(min = 3)
  private String username;

  @Size(min = 6)
  private String password;
}
