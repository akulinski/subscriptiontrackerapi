package com.akulinski.subscriptiontrackerapi.web.v1;

import com.akulinski.subscriptiontrackerapi.core.service.UserService;
import com.akulinski.subscriptiontrackerapi.core.service.dto.UserDTO;
import io.swagger.annotations.ApiImplicitParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@Slf4j
@RequiredArgsConstructor
public class UserResource {

  private final UserService userService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  @ApiImplicitParam(
      name = "Authorization",
      value = "Access Token",
      required = true,
      paramType = "header",
      dataTypeClass = String.class,
      example = "Bearer access_token")
  public UserDTO getUser() {
    return userService.getUser();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public UserDTO create(@RequestBody UserDTO userDTO) {
    return userService.create(userDTO);
  }

  @DeleteMapping("/{uuid}")
  @ResponseStatus(HttpStatus.OK)
  @ApiImplicitParam(
      name = "Authorization",
      value = "Access Token",
      required = true,
      paramType = "header",
      dataTypeClass = String.class,
      example = "Bearer access_token")
  public void delete() {
    userService.delete();
  }
}
