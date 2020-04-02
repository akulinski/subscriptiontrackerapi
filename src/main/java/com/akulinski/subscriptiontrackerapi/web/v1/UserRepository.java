package com.akulinski.subscriptiontrackerapi.web.v1;

import com.akulinski.subscriptiontrackerapi.core.service.UserService;
import com.akulinski.subscriptiontrackerapi.core.service.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@Slf4j
@RequiredArgsConstructor
public class UserRepository {

  private final UserService userService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public UserDTO create(@RequestBody UserDTO userDTO) {
    return userService.create(userDTO);
  }

  @DeleteMapping("/{uuid}")
  @ResponseStatus(HttpStatus.OK)
  public void delete(@PathVariable String uuid) {
    userService.delete(uuid);
  }
}
